package org.jexif.api;

import org.jexif.api.reader.JExifReaderFactory;
import org.jexif.api.writer.JExifWriterFactory;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public abstract class JExifProvider {

    private static final Object lock = new Object();
    private static JExifProvider provider = null;

    public abstract JExifReaderFactory createJExifReaderFactory() throws JExifProviderException;

    public abstract JExifWriterFactory createJExifWriterFactory() throws JExifProviderException;

    public static JExifProvider provider() {
        synchronized (lock) {
            if (provider != null)
                return provider;
            return AccessController.doPrivileged(
                    new PrivilegedAction<JExifProvider>() {
                        public JExifProvider run() {
                            if (loadProviderFromProperty())
                                return provider;
                            if (loadProviderAsService())
                                return provider;
                            provider = new JExifProviderImpl();
                            return provider;
                        }
                    });
        }
    }

    private static boolean loadProviderAsService() {
        ServiceLoader<JExifProvider> sl = ServiceLoader.load(JExifProvider.class, ClassLoader.getSystemClassLoader());
        Iterator<JExifProvider> i = sl.iterator();
        for (;;) {
            try {
                if (!i.hasNext())
                    return false;
                provider = i.next();
                return true;
            } catch (ServiceConfigurationError sce) {
                if (sce.getCause() instanceof SecurityException) {
                    // Ignore the security exception, try the next provider
                    continue;
                }
                throw sce;
            }
        }
    }

    private static boolean loadProviderFromProperty() {
        String cn = System.getProperty("org.jexif.api.JExifProvider");
        if (cn == null)
            return false;
        try {
            Class<?> c = Class.forName(cn, true, ClassLoader.getSystemClassLoader());
            provider = (JExifProvider)c.newInstance();
            return true;
        } catch (ClassNotFoundException x) {
            throw new ServiceConfigurationError(null, x);
        } catch (IllegalAccessException x) {
            throw new ServiceConfigurationError(null, x);
        } catch (InstantiationException x) {
            throw new ServiceConfigurationError(null, x);
        } catch (SecurityException x) {
            throw new ServiceConfigurationError(null, x);
        }
    }
}
