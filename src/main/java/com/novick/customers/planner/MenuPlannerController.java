package com.novick.customers.planner;

import com.novick.customers.menu.entities.AgeGroup;
import com.novick.customers.menu.service.AgeGroupService;
import com.novick.customers.planner.entities.MenuAgeGroup;
import com.novick.customers.planner.entities.MenuSchedule;
import com.novick.customers.planner.models.DayCalendar;
import com.novick.customers.planner.models.Menu;
import com.novick.customers.planner.models.WeekCalendar;
import com.novick.customers.planner.services.MenuAgeGroupService;
import com.novick.customers.planner.services.MenuScheduleService;
import com.novick.customers.planner.services.MenuService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class MenuPlannerController {

    private final MenuService menuService;
    private final MenuAgeGroupService menuAgeGroupService;
    private final MenuScheduleService menuScheduleService;
    private final Map<String, AgeGroup> ageGroupMap;

    public MenuPlannerController(MenuService menuService, MenuAgeGroupService menuAgeGroupService, MenuScheduleService menuScheduleService, AgeGroupService ageGroupService) {
        this.ageGroupMap = ageGroupService.ageGroupByParameterName();
        this.menuAgeGroupService = menuAgeGroupService;
        this.menuScheduleService = menuScheduleService;
        this.menuService = menuService;
    }

    @Transactional
    @PostMapping("/menu-planner")
    public String menuPlanner(@RequestBody Menu menu) {
        var entity = new com.novick.customers.planner.entities.Menu();
        entity.setName(menu.name());
        entity.setOid(menu.userId());
        entity.setStartDate(menu.date());
        var saved = menuService.save(entity);

        var servings = menu.individualsPerGroup();
        var values = new MenuAgeGroup[] {
                MenuAgeGroup.create(saved.getId(), ageGroupMap.get("ages-1-2").getId(), servings.ages1to2()),
                MenuAgeGroup.create(saved.getId(), ageGroupMap.get("ages-3-5").getId(), servings.ages3to5()),
                MenuAgeGroup.create(saved.getId(), ageGroupMap.get("ages-6-18").getId(), servings.ages6to18()),
                MenuAgeGroup.create(saved.getId(), ageGroupMap.get("adult").getId(), servings.adult())
        };

        Arrays.stream(values).forEach(this.menuAgeGroupService::save);

        var calendar = menu.mealCalendar();
        var weeks = new WeekCalendar[] { calendar.week1(), calendar.week2(), calendar.week3(), calendar.week4() };
        for (var i = 0; i < weeks.length; i++) {
            var week = weeks[i];
            if (week == null) {
                continue;
            }

            var days = new DayCalendar[]{ week.monday(), week.tuesday(), week.wednesday(), week.thursday(), week.friday() };
            for (var j = 0; j < days.length; j++) {
                var day = days[j];
                if (day == null) {
                    continue;
                }

                var recipe = day.recipe();
                var schedule = new MenuSchedule();
                schedule.setMenuId(saved.getId());
                schedule.setAgeGroupsId(recipe.getAgeGroup().id());
                schedule.setRecipesId(recipe.getId());
                schedule.setMealsId(recipe.getMealPattern().id());
                schedule.setWeek(i + 1);
                schedule.setDay(j + 1);
                menuScheduleService.save(schedule);
            }
        }

        return menu.userId();
    }

}
