package controllers;

import models.Territory;
import models.TerritoryDetail;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class TerritoryController extends Controller
{
    private JPAApi jpaApi;

    @Inject
    public TerritoryController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getTerritories()
    {
        List<TerritoryDetail> territories = jpaApi.em().createQuery("SELECT NEW models.TerritoryDetail(t.territoryId, t.territoryDescription, r.regionId, r.regionDescription) FROM Territory t JOIN Region r ON t.regionId = r.regionId ORDER BY territoryDescription").getResultList();

        return ok(views.html.territories.render(territories));


    }
}
