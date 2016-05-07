package org.kesler.mfc.routeforms.server.domain;

/**
 * Тип топлива
 */
public enum FuelType {
    AI92("АИ-92"),
    AI95("АИ-95"),
    DT("ДТ");

    private String desc;

    FuelType(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
