package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository ){
        this.menuItemRepository=menuItemRepository;
    }

    @Transactional
    public void bulkUpdate(Long id,List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            menuItem.setRestaurantId(id);
            menuItemRepository.save(menuItem);
        }
    }
}
