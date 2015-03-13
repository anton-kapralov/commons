package kae.util.android;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

/**
 * GeoLocationUtils
 *
 * @author Kapralov A.
 *         25.11.2013 16:38
 */
public class GeoLocationUtils {

  public static String getAddress(Context context, Location location) throws IOException {
    Geocoder gcd = new Geocoder(context, Locale.getDefault());
    List<Address> addresses =
        gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

    if (addresses.size() > 0) {
      final Address address = addresses.get(0);
      StringBuilder sb = new StringBuilder();
      sb.append(address.getCountryName());
      sb.append(", ");
      sb.append(address.getLocality());
      if (address.getMaxAddressLineIndex() > 0) {
        sb.append(", ");
        sb.append(address.getAddressLine(0));
      }

      return sb.toString();
    }

    return "";
  }

  public static String locationToString(Location location) {
    if (location != null) {
      return String.format(Locale.US, "%s %f %f %.2f", location.getProvider(),
          location.getLatitude(), location.getLongitude(), location.getAccuracy());
    } else {
      return "местоположение не определено";
    }
  }

  public static String coordinatesToString(Location location) {
    if (location != null) {
      return String.format(Locale.US, "%.6f %.6f (%s)",
          location.getLatitude(), location.getLongitude(), location.getProvider());
    } else {
      return "местоположение не определено";
    }
  }

  private static final double EQUATORIAL_EARTH_RADIUS = 6378137;

  public static double distance(Location location1, Location location2) {
    if (location1 != null && location2 != null) {
      return distance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(),
          location2.getLongitude());
    } else {
      return 0;
    }
  }

  public static boolean isInZone(double centerLat, double centerLon, float radius, double lat, double lon, float accuracy) {
    double distance = distance(centerLat, centerLon, lat, lon);
    return (distance - accuracy) <= radius;
  }

  public static double distance(double lat1, double lon1, double lat2, double lon2) {
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.asin(Math.sqrt(a));
    return EQUATORIAL_EARTH_RADIUS * c;
  }

  public static double angle(Location locationA, Location locationB, Location locationC) {
    double ab = distance(locationA, locationB);
    double bc = distance(locationB, locationC);
    double ac = distance(locationA, locationC);

    if (ab > 0 && bc > 0 && ac > 0) {
      return Math.acos((ab * ab + bc * bc - ac * ac) / (2 * ab * bc));
    } else {
      return 0;
    }
  }

  public static double deviationAngle(Location locationA, Location locationB, Location locationC) {
    return Math.PI - angle(locationA, locationB, locationC);
  }

}
