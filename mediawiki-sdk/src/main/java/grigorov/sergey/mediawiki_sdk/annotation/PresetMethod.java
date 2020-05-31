package grigorov.sergey.mediawiki_sdk.annotation;

public @interface PresetMethod {
    Class<?> type();
    String[] args();
}