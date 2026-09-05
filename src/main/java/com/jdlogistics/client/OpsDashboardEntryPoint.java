package com.jdlogistics.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import com.jdlogistics.DashboardModule.DashboardFilterWidget;
import com.jdlogistics.DashboardModule.KpiPanel;
import com.jdlogistics.DashboardModule.QuickActionsWidget;
import com.jdlogistics.DashboardModule.RecentAlertsWidget;
import com.jdlogistics.DashboardModule.ShipmentOverviewWidget;
import com.jdlogistics.DashboardModule.SidebarNav;

/**
 * Assembles the full Operations Dashboard using native SmartGWT layout
 * widgets (HLayout / VLayout) and the dashboard module widgets, matching
 * the "Smart GWT Operations Dashboard - Initial Wireframe".
 */
public class OpsDashboardEntryPoint implements EntryPoint {

    public void onModuleLoad() {
        HLayout root = new HLayout();
        root.setWidth100();
        root.setHeight100();

        root.addMember(new SidebarNav());
        root.addMember(buildMainContent());

        root.draw();
    }

    private VLayout buildMainContent() {
        VLayout main = new VLayout();
        main.setWidth("*");
        main.setHeight100();
        main.setPadding(16);
        main.setMembersMargin(16);
        main.setBackgroundColor("#f4f6f9");

        main.addMember(buildTopBar());
        main.addMember(new KpiPanel());
        main.addMember(buildContentRow());

        return main;
    }

    private HLayout buildTopBar() {
        HLayout topBar = new HLayout();
        topBar.setStyleName("jdTopBar");
        topBar.setHeight(48);
        topBar.setPadding(10);
        topBar.setAlign(com.smartgwt.client.types.VerticalAlignment.CENTER);

        Label title = new Label("Operations Dashboard");
        title.setStyleName("jdTopBarTitle");
        title.setWidth("*");

        Label bell = new Label("\uD83D\uDD14");
        bell.setWidth(30);
        bell.setAlign(com.smartgwt.client.types.Alignment.CENTER);

        Label admin = new Label("\uD83D\uDC64 Admin \u25BE");
        admin.setWidth(90);

        topBar.addMember(title);
        topBar.addMember(bell);
        topBar.addMember(admin);
        return topBar;
    }

    private HLayout buildContentRow() {
        HLayout row = new HLayout();
        row.setWidth100();
        row.setHeight("*");
        row.setMembersMargin(16);

        // Left: chart + quick actions stacked (~78% width)
        VLayout leftCol = new VLayout();
        leftCol.setWidth("55%");
        leftCol.setHeight100();
        leftCol.setMembersMargin(16);
        leftCol.addMember(new ShipmentOverviewWidget());
        leftCol.addMember(new QuickActionsWidget());

        // Middle: recent alerts grid (~25% width)
        RecentAlertsWidget alerts = new RecentAlertsWidget();
        alerts.setWidth("25%");
        alerts.setHeight100();

        // Right: filters form (~20% width)
        DashboardFilterWidget filters = new DashboardFilterWidget();
        filters.setWidth("20%");
        filters.setHeight100();

        row.addMember(leftCol);
        row.addMember(alerts);
        row.addMember(filters);
        return row;
    }
}
