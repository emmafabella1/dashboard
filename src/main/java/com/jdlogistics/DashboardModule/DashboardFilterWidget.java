package com.jdlogistics.DashboardModule;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * "Filters" card — a native SmartGWT DynamicForm with SelectItems
 * (Date Range / Service / Status) and an Apply Filters button.
 */
public class DashboardFilterWidget extends VLayout {

    private SelectItem dateRangeItem;
    private SelectItem serviceItem;
    private SelectItem statusItem;

    public DashboardFilterWidget() {
        setStyleName("jdCard");
        setPadding(12);
        setHeight100();
        setMembersMargin(8);

        Label title = new Label("Filters");
        title.setStyleName("jdCardTitle");
        title.setHeight(20);

        addMember(title);
        addMember(buildForm());
        addMember(buildApplyButton());
    }

    private DynamicForm buildForm() {
        DynamicForm form = new DynamicForm();
        form.setWidth100();
        form.setNumCols(2);

        dateRangeItem = new SelectItem("dateRange", "Date Range");
        dateRangeItem.setValueMap("Today", "Last 7 Days", "Last 30 Days", "Custom Range");
        dateRangeItem.setDefaultValue("Last 7 Days");
        dateRangeItem.setWidth(150);

        serviceItem = new SelectItem("service", "Service");
        serviceItem.setValueMap("All", "Shipment Service", "Tracking Service", "Order Service");
        serviceItem.setDefaultValue("All");
        serviceItem.setWidth(150);

        statusItem = new SelectItem("status", "Status");
        statusItem.setValueMap("All", "Delivered", "In Transit", "Delayed", "Exception");
        statusItem.setDefaultValue("All");
        statusItem.setWidth(150);

        form.setFields(dateRangeItem, serviceItem, statusItem);
        return form;
    }

    private IButton buildApplyButton() {
        IButton apply = new IButton("Apply Filters");
        apply.setIcon("actions/filter.png");
        apply.setWidth100();
        apply.addClickHandler((ClickEvent event) -> Window.alert(
                "Filters applied: " + dateRangeItem.getValueAsString()
                        + " / " + serviceItem.getValueAsString()
                        + " / " + statusItem.getValueAsString()));
        return apply;
    }
}
