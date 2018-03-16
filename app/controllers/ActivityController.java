package controllers;

import models.Activity;
import models.Park;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Int;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ActivityController extends Controller
{
    @Inject
    private FormFactory formFactory;
    private JPAApi jpaApi;

    @Inject
    public ActivityController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getActivities()
    {
        List<Activity> activities = jpaApi.
                em().
                createQuery("SELECT a FROM Activity a")
                .getResultList();

        return ok(views.html.findpark.render(activities));
    }

    @Transactional(readOnly = true)
    public Result postActivitySearchResults()
    {
        String activityIds[] = request().body().asFormUrlEncoded().get("activityId[]");
        int activityRequestLength = activityIds.length;
        Long requestSize = new Long(activityRequestLength);
        List<Integer> activityIdValues = new ArrayList<>(activityRequestLength);

        for(String activityId : activityIds)
        {
            activityIdValues.add(new Integer(activityId));
        }

        //TODO Join Park and Activity to return parks with the selected activities
        String sql = "SELECT p " +
                "FROM Park p " +
                "JOIN ParkActivity pa ON pa.parkId = p.parkId " +
                "WHERE pa.activityId IN (:activityIds) " +
                "GROUP BY p " +
                "HAVING count(*) = (:activityRequestLength) " +
                "ORDER BY p.parkId";

                //"SELECT a FROM Activity a WHERE ActivityId IN (:activityIds)"

        List<Park> parks = jpaApi.em().createQuery(sql).setParameter("activityIds", activityIdValues).
                setParameter("activityRequestLength", requestSize).getResultList();

        return ok("" + parks.size());

    }


}
