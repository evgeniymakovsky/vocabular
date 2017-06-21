package com.itibo.controller;

import com.itibo.entity.User;
import com.itibo.entity.Word;
import com.itibo.service.UserService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Class SettingsController is managed bean for /views/statistics.xhtml view.
 */
@ManagedBean
@ViewScoped
@Component
public class StatisticsController {

    @Autowired
    @ManagedProperty("#{UserService}")
    private UserService userService;

    private HorizontalBarChartModel horizontalBarModel;
    private List<Word> words;
    private String statistics;

    /**
     * Method createHorizontalBarModel invokes for creating Primefaces Horizontal Bar Model.
     */
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();

        ChartSeries score = new ChartSeries();
        score.setLabel("Score");
        for (Word word : words) {
            score.set(word.getEnglish(), word.getScore());
        }

        ChartSeries repeats = new ChartSeries();
        repeats.setLabel("Repeats");
        int maxAxis = 0;
        for (Word word : words) {
            repeats.set(word.getEnglish(), word.getRepeated());
            if (maxAxis < word.getRepeated()) {
                maxAxis = word.getRepeated() + word.getScore() + 10;
            }
        }

        horizontalBarModel.addSeries(score);
        horizontalBarModel.addSeries(repeats);

        horizontalBarModel.setTitle("Relation between repeated and score");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Relation");
        xAxis.setMin(0);
        xAxis.setMax(maxAxis);

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Words");
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        createHorizontalBarModel();
        return horizontalBarModel;
    }

    /**
     * Init method for StatisticsController
     * @return URL statistics.xhtml
     */
    public String getStatistics() {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        words = user.getWords();
        statistics = "statistics.xhtml?faces-redirect=true";
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
