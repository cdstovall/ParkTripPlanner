package controllers;

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

/*
    @Transactional
    public Result getParks()
    {
        List<Park> parks = jpaApi.
                em().
                createQuery("SELECT p FROM Park p")
                .getResultList();

        return ok(views.html.findpark.render(parks));
    }
*/
}
