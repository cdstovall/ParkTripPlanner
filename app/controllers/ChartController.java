package controllers;

import models.ActivityCount;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ChartController extends Controller
{
    @Inject
    private FormFactory formFactory;
    private JPAApi jpaApi;

    @Inject
    public ChartController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    public Result getChart()
    {
        return ok(views.html.chart.render());
    }

    @Transactional (readOnly = true)
    public Result getActivityChart()
    {
        String sql = "SELECT NEW ActivityCount(a.activityId, a.activityName, COUNT(*)) " +
                "FROM Activity a " +
                "JOIN ParkActivity pa ON a.activityId = pa.activityId " +
                "GROUP BY a.activityName";

        List<ActivityCount> activityCounts = jpaApi.em().createQuery(sql, ActivityCount.class).getResultList();

        return ok(views.html.activitychart.render(activityCounts));
    }

}
