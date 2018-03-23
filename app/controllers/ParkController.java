package controllers;

import models.Activity;
import models.Park;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ParkController extends Controller
{
    private JPAApi jpaApi;

    @Inject
    public ParkController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    @Transactional (readOnly = true)
    public Result getPark(int parkId)
    {
        Park park = jpaApi.em().createQuery("SELECT p FROM Park p WHERE parkId = :parkId", Park.class).
                setParameter("parkId", parkId).getSingleResult();

        String sql = "SELECT a " +
                "FROM Activity a " +
                "JOIN ParkActivity pa ON a.activityId = pa.activityId " +
                "JOIN Park p ON pa.parkId = p.parkId " +
                "WHERE p.parkId = (:parkId)";

        List<Activity> parkActivities = jpaApi.
                em().
                createQuery(sql, Activity.class).
                setParameter("parkId", parkId).
                getResultList();

        return ok(views.html.park.render(park, parkActivities));
    }

    /*
    @Transactional(readOnly = true)
    public Result getPicture(int id)
    {
        Park park = jpaApi.
                em().
                createQuery("SELECT p FROM Park p WHERE parkId = :parkId", Park.class).
                setParameter("parkId", id).
                getSingleResult();

        return ok(park.getPicture()).as("image/jpg");
    }
*/
}
