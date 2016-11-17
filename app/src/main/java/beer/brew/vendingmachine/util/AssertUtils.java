package beer.brew.vendingmachine.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.io.Serializable;
import beer.brew.vendingmachine.util.sign.AlipayConfig;

public class AssertUtils {

    public static <T extends Serializable> T getContentFromAssert(Context context, String assetsDir, Class<T> clz) throws Exception {
        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open(assetsDir);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        String content = new String(buffer);
        return JsonUtils.fromJson(content, clz);
    }
}
