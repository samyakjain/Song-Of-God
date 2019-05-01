package song.of.god.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetUtil {

        /**
         * Determine if the network connection is open, including the mobile data connection
         */
        public static boolean isNetworkAvailable(Context context) {
            boolean netstate = false;
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {

                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {

                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                            netstate = true;
                            break;
                        }
                    }
                }
            }
            return netstate;
        }

        public static boolean isGpsEnabled(Context context) {
            LocationManager lm = (LocationManager) context
                    .getSystemService(Context.LOCATION_SERVICE);
            return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        /**
         * Check if the currently open network type is WIFI
         */
        public static boolean isWifi(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }

        /**
         * Check if the currently open network type is 3G
         */
        public static boolean is3G(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }

        /**
         * Check if the currently open network type is 4G
         */
        public static boolean is4G(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) {
                if (activeNetInfo.getType() == TelephonyManager.NETWORK_TYPE_LTE) {
                    return true;
                }
            }
            return false;
        }

        /**
         * IP address check
         */
        public static boolean isIP(String ip) {
            Pattern pattern = Pattern
                    .compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        }

        public enum NetState {
            NET_NO, NET_2G, NET_3G, NET_4G, NET_WIFI, NET_UNKNOWN
        }

        /**
         * Determine wats the network connection is currently
         */
        public NetState isConnected(Context context) {
            NetState stateCode = NetState.NET_NO;
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.isConnectedOrConnecting()) {
                switch (ni.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        stateCode = NetState.NET_WIFI;
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        switch (ni.getSubtype()) {
                            case TelephonyManager.NETWORK_TYPE_GPRS:
                            case TelephonyManager.NETWORK_TYPE_CDMA:
                            case TelephonyManager.NETWORK_TYPE_EDGE:
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                stateCode = NetState.NET_2G;
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                            case TelephonyManager.NETWORK_TYPE_HSDPA:
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                            case TelephonyManager.NETWORK_TYPE_HSPA:
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                            case TelephonyManager.NETWORK_TYPE_EHRPD:
                            case TelephonyManager.NETWORK_TYPE_HSPAP:
                                stateCode = NetState.NET_3G;
                                break;
                            case TelephonyManager.NETWORK_TYPE_LTE:
                                stateCode = NetState.NET_4G;
                                break;
                            default:
                                stateCode = NetState.NET_UNKNOWN;
                        }
                        break;
                    default:
                        stateCode = NetState.NET_UNKNOWN;
                }

            }
            return stateCode;
        }

        /**
         * Returns the map of url parameters
         */
        public static Map<String, String> getUrlParams(String url) {
            Map<String, String> params = null;
            try {
                String[] urlParts = url.split("\\?");
                if (urlParts.length > 1) {
                    params = new HashMap<>();
                    String query = urlParts[1];
                    for (String param : query.split("&")) {
                        String[] pair = param.split("=");
                        String key = URLDecoder.decode(pair[0], "UTF-8");
                        String value = "";
                        if (pair.length > 1) {
                            value = URLDecoder.decode(pair[1], "UTF-8");
                        }
                        params.put(key, value);
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            return params;
        }

    }
