package app.snob.busservice.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BusStatus {
    BROKEN("Broken"),
    REPAIRING("Repairing"),
    ACTIVE("Active"),
    PRESERVED("Preserved");

    private final String displayName;
    @Override
    public String toString() {
        return displayName;
    }
}
