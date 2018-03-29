package controllers;

import Services.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.Logger;
import play.api.Configuration;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkController extends Controller
{
    private JPAApi jpaApi;
    private Configuration configuration;

    @Inject
    public ParkController(JPAApi jpaApi, Configuration configuration)
    {
        this.jpaApi = jpaApi;
        this.configuration = configuration;
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

        String apiKey = getConfValue();

        return ok(views.html.park.render(park, parkActivities, apiKey));
    }

    public Result getHome()
    {
        return ok(views.html.home.render());
    }


    public String getConfValue()
    {
        String value = configuration.underlying().getString("api.google.key");

        return value;
    }

    @Transactional (readOnly = true)
    public Result postSendEmail(Integer parkId)
    {
        Date date = new Date();

        Park park = jpaApi.em().createQuery("SELECT p FROM Park p WHERE parkId = :parkId", Park.class).
                setParameter("parkId", parkId).getSingleResult();

        Email.sendEmail(date, park);

        return ok(views.html.confirmation.render());
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
