package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.Logger;
import play.api.Configuration;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityController extends Controller
{
    @Inject
    private FormFactory formFactory;
    private JPAApi jpaApi;
    private Configuration configuration;

    @Inject
    public ActivityController(FormFactory formFactory, JPAApi jpaApi, Configuration configuration)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
        this.configuration = configuration;
    }

    @Transactional
    public Result getActivities()
    {
        List<Activity> activities = jpaApi.
                em().
                createQuery("SELECT a FROM Activity a")
                .getResultList();

        return ok(views.html.findparks.render(activities));
    }

    //Original, returns parks meeting activity criteria without distance considerations
    /*
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

        return ok(views.html.parkchoices.render(parks));

    }*/

    @Transactional
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

        String sql = "SELECT p " +
                "FROM Park p " +
                "JOIN ParkActivity pa ON pa.parkId = p.parkId " +
                "WHERE pa.activityId IN (:activityIds) " +
                "GROUP BY p " +
                "HAVING count(*) = (:activityRequestLength)";

        //"SELECT a FROM Activity a WHERE ActivityId IN (:activityIds)"

        List<Park> parks = jpaApi.em().createQuery(sql).setParameter("activityIds", activityIdValues).
                setParameter("activityRequestLength", requestSize).getResultList();

        for(Park park : parks)
        {

            try
            {
                URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/" +
                        "json?units=imperial&origins=35.090791,-92.4421768&destinations=" + park.getLatitude() + "," + park.getLongitude() +
                        "&key=" + getConfValue());
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();
                Logger.debug("distance status: " + request.getResponseCode());

                ObjectMapper objectMapper = new ObjectMapper();
                DistanceMatrix distanceMatrix = objectMapper.readValue(url, DistanceMatrix.class);

                //Gets individual travel results from JSON data
                Row row = distanceMatrix.getRows().get(0);
                Element element = row.getElements().get(0);

                Duration duration = element.getDuration();
                Integer durationValue = duration.getValue();
                String durationText = duration.getText();


                park.setDurationValue(durationValue);
                park.setDurationText(durationText);
                jpaApi.em().persist(park);

            }
            catch (Exception e)
            {
                Logger.debug("Unable to get distance and duration.");
                Logger.debug(e.toString());
            }
        }

        String sql2 = "SELECT p " +
                "FROM Park p " +
                "WHERE p IN (:parks) " +
                "ORDER BY p.durationValue";

        //"SELECT a FROM Activity a WHERE ActivityId IN (:activityIds)"

        List<Park> parks2 = jpaApi.em().createQuery(sql2).setParameter("parks", parks).getResultList();


        return ok(views.html.parkchoices.render(parks2));

    }

    public String getConfValue()
    {
        String value = configuration.underlying().getString("api.google.key");

        return value;
    }

}
