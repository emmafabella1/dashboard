package com.jdlogistics.DashboardModule;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Left navigation sidebar: JD Logistics logo, Dashboard link, and grouped
 * sections (Services / Reports / Admin) plus Logout — matching the wireframe.
 */
public class SidebarNav extends VLayout {

    public SidebarNav() {
        setWidth(230);
        setHeight100();
        setStyleName("jdSidebar");

        addMember(logo());
        addMember(navItem("\uD83C\uDFE0 Dashboard", true));

        addMember(sectionLabel("Services"));
        addMember(navItem("\uD83D\uDE9A Shipment Service", false));
        addMember(navItem("\uD83D\uDCCD Tracking Service", false));
        addMember(navItem("\uD83D\uDCCB Order Service", false));

        addMember(sectionLabel("Reports"));
        addMember(navItem("\uD83D\uDCCA Operational Report", false));
        addMember(navItem("\uD83D\uDCDD Audit Log", false));

        addMember(sectionLabel("Admin"));
        addMember(navItem("\uD83D\uDC65 User Management", false));
        addMember(navItem("\u2699 System Config", false));

        VLayout spacer = new VLayout();
        spacer.setHeight("*");
        addMember(spacer);

        addMember(navItem("\u21AA Logout", false));
    }

    private Label logo() {
        Label logo = new Label("\uD83D\uDCE6 JD Logistics");
        logo.setStyleName("jdSidebarLogo");
        logo.setHeight(50);
        return logo;
    }

    private Label sectionLabel(String text) {
        Label label = new Label(text);
        label.setStyleName("jdSidebarSectionLabel");
        label.setHeight(24);
        return label;
    }

    private Label navItem(String text, boolean active) {
        Label item = new Label(text);
        item.setStyleName(active ? "jdSidebarItemActive" : "jdSidebarItem");
        item.setHeight(34);
        return item;
    }
}
