package au.com.auspost.smartspb.web.value.ame;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class AddressMatchResponseVO {

    public static final Pattern ADDRESS_RANGE_PATTERN = Pattern.compile("(\\b\\d*\\s*\\/?\\s*\\d+\\s*-\\s*\\d+\\b)");

    private UUID id;
    private List<Results> results = new ArrayList<>();

    public AddressMatchResponseVO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public void addToResultsList(Results results) {
        this.results.add(results);
    }

    public static class Results {
        private String id;

        private List<Address> addresses = new ArrayList<>();

        public Results() {

        }

        public Results(String id, List<Address> addresses) {
            this.id = id;
            this.addresses = addresses;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }
    }

    public static class Address {
        private String unstructured;
        private BigDecimal score;
        private SemiStructured semiStructured;
        private DeliveryData deliveryData;
        private Structured structured;
        private List<GeoData> geoDataList;
        private Surrounding surrounding;
        private Integer dpid;
        private BigDecimal distance;

        public enum Confidence{
            HIGH,
            MEDIUM,
            LOW
        }

        public enum GeoConfidence {
            HIGH,
            MEDIUM,
            LOW
        }

        public enum GeoFeatureType {
            CENTROID("Centroid");

            private String value;

            GeoFeatureType(String value) {
                this.value = value;
            }

            public String toString() {
                return value;
            }
        }

        private Confidence confidence;

        public String getUnstructured() {
            return unstructured;
        }

        public void setUnstructured(String unstructured) {
            this.unstructured = unstructured;
        }

        public BigDecimal getScore() {
            return score;
        }

        public void setScore(BigDecimal score) {
            this.score = score;
        }

        public SemiStructured getSemiStructured() {
            return semiStructured;
        }

        public void setSemiStructured(SemiStructured semiStructured) {
            this.semiStructured = semiStructured;
        }

        public Integer getDpid() {
            return dpid;
        }

        public void setDpid(Integer dpid) {
            this.dpid = dpid;
        }

        public BigDecimal getDistance() {
            return distance;
        }

        public void setDistance(BigDecimal distance) {
            this.distance = distance;
        }

        public Structured getStructured() {
            return structured;
        }

        public void setStructured(Structured structured) {
            this.structured = structured;
        }

        public DeliveryData getDeliveryData() {
            return deliveryData;
        }

        public void setDeliveryData(DeliveryData deliveryData) {
            this.deliveryData = deliveryData;
        }

        public List<GeoData> getGeoDataList() {
            return geoDataList;
        }

        public void setGeoDataList(List<GeoData> geoDataList) {
            this.geoDataList = geoDataList;
        }

        public Confidence getConfidence() {
            return confidence;
        }

        public void setConfidence(Confidence confidence) {
            this.confidence = confidence;
        }

        public Surrounding getSurrounding() {
            return surrounding;
        }

        public void setSurrounding(Surrounding surrounding) {
            this.surrounding = surrounding;
        }

        public static class SemiStructured {
            private String addressLine1;
            private String locality;
            private String state;
            private Integer postcode;
            private String countryName = "AUSTRALIA";
            private String countryCode = "AU";

            public String getAddressLine1() {
                return addressLine1;
            }

            public void setAddressLine1(String addressLine1) {
                this.addressLine1 = addressLine1;
            }

            public String getLocality() {
                return locality;
            }

            public void setLocality(String locality) {
                this.locality = locality;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Integer getPostcode() {
                return postcode;
            }

            public void setPostcode(Integer postcode) {
                this.postcode = postcode;
            }

            public String getCountryName() {
                return countryName;
            }

            public void setCountryName(String countryName) {
                this.countryName = countryName;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }
        }

        public static class Structured {
            private String locality;
            private String state;
            private Integer postcode;
            private String countryName = "AUSTRALIA";
            private String countryCode = "AU";
            private String flatOrUnitType;
            private String flatOrUnitNumber;
            private String buildingLevelType;
            private String buildingLevelNumer;
            private String thoroughfareName;
            private String thoroughfareNumber1;
            private String thoroughfareNumber2;
            private String thoroughfareType;
            private String thoroughfareSuffix;
            private String postalDeliveryType;
            private String postalDeliveryNumber;
            private String lotNumber;
            private Integer deliveryPointIdentifier;

            public String getLocality() {
                return locality;
            }

            public void setLocality(String locality) {
                this.locality = locality;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Integer getPostcode() {
                return postcode;
            }

            public void setPostcode(Integer postcode) {
                this.postcode = postcode;
            }

            public String getCountryName() {
                return countryName;
            }

            public void setCountryName(String countryName) {
                this.countryName = countryName;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public String getFlatOrUnitType() {
                return flatOrUnitType;
            }

            public void setFlatOrUnitType(String flatOrUnitType) {
                this.flatOrUnitType = flatOrUnitType;
            }

            public String getFlatOrUnitNumber() {
                return flatOrUnitNumber;
            }

            public void setFlatOrUnitNumber(String flatOrUnitNumber) {
                this.flatOrUnitNumber = flatOrUnitNumber;
            }

            public String getBuildingLevelType() {
                return buildingLevelType;
            }

            public void setBuildingLevelType(String buildingLevelType) {
                this.buildingLevelType = buildingLevelType;
            }

            public String getBuildingLevelNumer() {
                return buildingLevelNumer;
            }

            public void setBuildingLevelNumer(String buildingLevelNumer) {
                this.buildingLevelNumer = buildingLevelNumer;
            }

            public String getThoroughfareName() {
                return thoroughfareName;
            }

            public void setThoroughfareName(String thoroughfareName) {
                this.thoroughfareName = thoroughfareName;
            }

            public String getThoroughfareNumber1() {
                return thoroughfareNumber1;
            }

            public void setThoroughfareNumber1(String thoroughfareNumber1) {
                this.thoroughfareNumber1 = thoroughfareNumber1;
            }

            public String getThoroughfareNumber2() {
                return thoroughfareNumber2;
            }

            public void setThoroughfareNumber2(String thoroughfareNumber2) {
                this.thoroughfareNumber2 = thoroughfareNumber2;
            }

            public String getThoroughfareType() {
                return thoroughfareType;
            }

            public void setThoroughfareType(String thoroughfareType) {
                this.thoroughfareType = thoroughfareType;
            }

            public String getThoroughfareSuffix() {
                return thoroughfareSuffix;
            }

            public void setThoroughfareSuffix(String thoroughfareSuffix) {
                this.thoroughfareSuffix = thoroughfareSuffix;
            }

            public String getPostalDeliveryType() {
                return postalDeliveryType;
            }

            public void setPostalDeliveryType(String postalDeliveryType) {
                this.postalDeliveryType = postalDeliveryType;
            }

            public String getPostalDeliveryNumber() {
                return postalDeliveryNumber;
            }

            public void setPostalDeliveryNumber(String postalDeliveryNumber) {
                this.postalDeliveryNumber = postalDeliveryNumber;
            }

            public String getLotNumber() {
                return lotNumber;
            }

            public void setLotNumber(String lotNumber) {
                this.lotNumber = lotNumber;
            }

            public Integer getDeliveryPointIdentifier() {
                return deliveryPointIdentifier;
            }

            public void setDeliveryPointIdentifier(Integer deliveryPointIdentifier) {
                this.deliveryPointIdentifier = deliveryPointIdentifier;
            }
        }

        public static class DeliveryData {
            private Integer postcodeDID;
            private Integer localityDID;
            private Integer groupDID;
            private Integer roundDID;
            private Integer sectionDID;
            private Integer DPID;
            private Integer sectionSequence;
            private Integer deliveryPointSequence;
            private Integer deliveryOfficeRoundId;

            public Integer getPostcodeDID() {
                return postcodeDID;
            }

            public void setPostcodeDID(Integer postcodeDID) {
                this.postcodeDID = postcodeDID;
            }

            public Integer getLocalityDID() {
                return localityDID;
            }

            public void setLocalityDID(Integer localityDID) {
                this.localityDID = localityDID;
            }

            public Integer getGroupDID() {
                return groupDID;
            }

            public void setGroupDID(Integer groupDID) {
                this.groupDID = groupDID;
            }

            public Integer getRoundDID() {
                return roundDID;
            }

            public void setRoundDID(Integer roundDID) {
                this.roundDID = roundDID;
            }

            public Integer getSectionDID() {
                return sectionDID;
            }

            public void setSectionDID(Integer sectionDID) {
                this.sectionDID = sectionDID;
            }

            public Integer getDPID() {
                return DPID;
            }

            public void setDPID(Integer DPID) {
                this.DPID = DPID;
            }

            public Integer getSectionSequence() {
                return sectionSequence;
            }

            public void setSectionSequence(Integer sectionSequence) {
                this.sectionSequence = sectionSequence;
            }

            public Integer getDeliveryPointSequence() {
                return deliveryPointSequence;
            }

            public void setDeliveryPointSequence(Integer deliveryPointSequence) {
                this.deliveryPointSequence = deliveryPointSequence;
            }

            public Integer getDeliveryOfficeRoundId() {
                return deliveryOfficeRoundId;
            }

            public void setDeliveryOfficeRoundId(Integer deliveryOfficeRoundId) {
                this.deliveryOfficeRoundId = deliveryOfficeRoundId;
            }
        }

        public static class GeoData {

            private GeoFeatureType featureType;
            private GeoConfidence confidence;
            private BigDecimal latitude;
            private BigDecimal longitude;

            public GeoFeatureType getFeatureType() {
                return featureType;
            }

            public void setFeatureType(GeoFeatureType featureType) {
                this.featureType = featureType;
            }

            public BigDecimal getLatitude() {
                return latitude;
            }

            public void setLatitude(BigDecimal latitude) {
                this.latitude = latitude;
            }

            public BigDecimal getLongitude() {
                return longitude;
            }

            public void setLongitude(BigDecimal longitude) {
                this.longitude = longitude;
            }

            public GeoConfidence getConfidence() {
                return confidence;
            }

            public void setConfidence(GeoConfidence confidence) {
                this.confidence = confidence;
            }

            public boolean sameLocation(GeoData other) {
                return latitude.equals(other.getLatitude()) && longitude.equals(other.getLongitude());
            }

            /*
             * Sourced from http://www.geodatasource.com/developers/java
             */
            public int distanceMetres(GeoData other) {
                double lat1 = latitude.doubleValue();
                double lon1 = longitude.doubleValue();
                double lat2 = other.getLatitude().doubleValue();
                double lon2 = other.getLongitude().doubleValue();

                double theta = lon1 - lon2;
                double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
                dist = Math.acos(dist);
                dist = rad2deg(dist);
                dist = dist * 60 * 1.1515; // Nautical miles
                dist = dist * 1.609344; // KM
                dist = dist * 1000; // Metres

                return new Double(dist).intValue();
            }

            private static double deg2rad(double deg) {
                return (deg * Math.PI / 180.0);
            }

            private static double rad2deg(double rad) {
                return (rad * 180 / Math.PI);
            }
        }

        public static class Surrounding {
            private List<String> surroundingLocalityNames;
            private List<Integer> surroundingLocalityPostcodes;

            public List<String> getSurroundingLocalityNames() {
                return surroundingLocalityNames;
            }

            public void setSurroundingLocalityNames(List<String> surroundingLocalityNames) {
                this.surroundingLocalityNames = surroundingLocalityNames;
            }

            public List<Integer> getSurroundingLocalityPostcodes() {
                return surroundingLocalityPostcodes;
            }

            public void setSurroundingLocalityPostcodes(List<Integer> surroundingLocalityPostcodes) {
                this.surroundingLocalityPostcodes = surroundingLocalityPostcodes;
            }
        }
    }
}