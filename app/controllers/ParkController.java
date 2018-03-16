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

    @Transactional (readOnly = true)
    public Result getPark(int parkId)
    {
        Park park = jpaApi.em().createQuery("SELECT p FROM Park p WHERE parkId = :parkId", Park.class).
                setParameter("parkId", parkId).getSingleResult();

        return ok(views.html.park.render(park));
    }

}
