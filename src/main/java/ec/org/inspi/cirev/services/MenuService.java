package ec.org.inspi.cirev.services;

import java.util.List;

import ec.org.inspi.cirev.models.Menu;
import ec.org.inspi.cirev.payload.request.MenuRequest;

public interface MenuService {
	public List<Menu> findMenu(MenuRequest menuRequest);
}
