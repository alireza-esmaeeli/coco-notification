package gl.coco.notification.model.notification;

public enum NotificationType {
    WARNING("https://files.coco.gl/d/TmPNR3wVcy3otym"),
    INFO("https://files.coco.gl/d/doz7cKPQYv8TQoO"),
    DISCOUNT("https://files.coco.gl/d/uI_FX3KK9SzRJX5");

    private final String iconUrl;

    NotificationType(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }
}
