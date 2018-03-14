package controllers;

import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;

import javax.inject.Inject;

public class ParkController extends Controller
{
    private JPAApi jpaApi;

    @Inject
    public ParkController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public result getParks()
    {
        //TODO return list of parks
    }


}
