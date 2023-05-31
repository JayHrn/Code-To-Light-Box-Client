package com.nchu.software.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;

/**
 * 字符编码
 */
public class UTF8Control extends ResourceBundle.Control implements ResourceBundleControlProvider {
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        InputStream stream = loader.getResourceAsStream(resourceName);
        if (stream != null) {
            try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                return new PropertyResourceBundle(reader);
            }
        }
        return super.newBundle(baseName, locale, format, loader, reload);
    }

    @Override
    public ResourceBundle.Control getControl(String baseName) {
        return null;
    }
}
