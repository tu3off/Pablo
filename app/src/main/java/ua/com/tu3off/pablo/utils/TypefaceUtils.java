package ua.com.tu3off.pablo.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public final class TypefaceUtils {

    private static final String TAG = TypefaceUtils.class.getSimpleName();
    private static final HashMap<String, SoftReference<Typeface>> cache = new HashMap<>();

    private TypefaceUtils() {
    }

    public static void setTypeface(TextView view, String typefacePath) {
        final AssetManager assetManager = view.getContext().getAssets();
        view.setTypeface(get(typefacePath, assetManager));
    }

    public static Typeface get(String typefacePath, AssetManager assetManager) {
        final SoftReference<Typeface> cachedTypefaceReference = cache.get(typefacePath);
        if (cachedTypefaceReference == null) {
            return createTypefaceAndPushToCache(typefacePath, assetManager);
        }
        final Typeface typeface = cachedTypefaceReference.get();
        if (typeface == null) {
            return createTypefaceAndPushToCache(typefacePath, assetManager);
        }
        return typeface;
    }

    public static void clearCache() {
        cache.clear();
    }

    private static Typeface createTypefaceAndPushToCache(String typefacePath,
                                                         AssetManager assetManager) {
        final Typeface typeface = createTypefaceFromPath(typefacePath, assetManager);
        pushToCache(typefacePath, typeface);
        return typeface;
    }

    private static void pushToCache(String typefacePath, Typeface typeface) {
        if (typeface != null) {
            cache.put(typefacePath, new SoftReference<>(typeface));
        }
    }

    private static Typeface createTypefaceFromPath(String typefacePath, AssetManager assetManager) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(assetManager, typefacePath);
        } catch (RuntimeException exception) {
            Log.e(TAG, exception.getMessage(), exception);
        }
        return typeface;
    }
}