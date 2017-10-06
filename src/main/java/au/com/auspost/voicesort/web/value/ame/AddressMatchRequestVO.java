package au.com.auspost.voicesort.web.value.ame;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressMatchRequestVO {
    private UUID id;
    private List<Address> addresses = new ArrayList<>();
    private Filters filters;
    private Detail detail;
    private boolean predictive;
    private Integer maxResults;

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public AddressMatchRequestVO putFilters(Filters filters) {
        this.filters = filters;
        return this;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public AddressMatchRequestVO putDetail(Detail detail){
        this.detail = detail;
        return this;
    }

    public boolean isPredictive() {
        return predictive;
    }

    public void setPredictive(boolean predictive) {
        this.predictive = predictive;
    }

    public AddressMatchRequestVO putPredictive(boolean predictive) {
        this.predictive = predictive;
        return this;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public AddressMatchRequestVO putMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AddressMatchRequestVO putId(UUID id) {
        this.id = id;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public AddressMatchRequestVO addAddress(Address address) {
        addresses.add(address);
        return this;
    }

    public static class Address {
        private List<Integer> dpids = new ArrayList<>();
        private String id;
        private String text;
        private String locality;
        private String state;
        private String postcode;
        private String country;
        private Geocoord geocoord;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Address putId(String id) {
            this.id = id;
            return this;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Address putText(String text) {
            this.text = text;
            return this;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public Address putLocality(String locality) {
            this.locality = locality;
            return this;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Address putState(String state) {
            this.state = state;
            return this;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public Address putPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Address putCountry(String country) {
            this.country = country;
            return this;
        }

        public List<Integer> getDpids() {
            return dpids;
        }

        public void setDpids(List<Integer> dpids) {
            this.dpids = dpids;
        }

        public Address addDpid(Integer dpid) {
            this.dpids.add(dpid);
            return this;
        }

        public Geocoord getGeocoord() {
            return geocoord;
        }

        public void setGeocoord(Geocoord geocoord) {
            this.geocoord = geocoord;
        }

        public Address putGeocoord(Geocoord geocoord) {
            this.geocoord = geocoord;
            return this;
        }

        public static class Geocoord {
            private BigDecimal latitude;
            private BigDecimal longitude;

            public BigDecimal getLatitude() {
                return latitude;
            }

            public void setLatitude(BigDecimal latitude) {
                this.latitude = latitude;
            }

            public Geocoord putLatitude(BigDecimal latitude) {
                this.latitude = latitude;
                return this;
            }

            public BigDecimal getLongitude() {
                return longitude;
            }

            public void setLongitude(BigDecimal longitude) {
                this.longitude = longitude;
            }

            public Geocoord putLongitude(BigDecimal longitude) {
                this.longitude = longitude;
                return this;
            }

            @Override
            public String toString() {
                return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
            }

        }
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public static class Filters {

        public Filters() {
        }

        public enum AddressType {
            STREET,
            NON_STREET,
            ALL
        }

        private List<Integer> rounds;
        private List<Integer> postcodes;
        private List<Integer> deliveryOfficeRoundIds;
        private AddressType addressType;
        private Geo geo;

        private List<String> postalDeliveryTypes;

        public List<Integer> getRounds() {
            return rounds;
        }

        public void setRounds(List<Integer> rounds) {
            this.rounds = rounds;
        }

        public Filters addRound(Integer round) {
            if (rounds == null) {
                rounds = new ArrayList<>();
            }
            rounds.add(round);
            return this;
        }

        public List<Integer> getPostcodes() {
            return postcodes;
        }

        public void setPostcodes(List<Integer> postcodes) {
            this.postcodes = postcodes;
        }

        public Filters addPostcode(Integer postcode) {
            if (postcodes == null) {
                postcodes = new ArrayList<>();
            }
            postcodes.add(postcode);
            return this;
        }

        public AddressType getAddressType() {
            return addressType;
        }

        public void setAddressType(AddressType addressType) {
            this.addressType = addressType;
        }

        public Filters putAddressType(AddressType addressType) {
            this.addressType = addressType;
            return this;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        public List<String> getPostalDeliveryTypes() {
            return postalDeliveryTypes;
        }

        public void setPostalDeliveryTypes(List<String> postalDeliveryTypes) {
            this.postalDeliveryTypes = postalDeliveryTypes;
        }

        public List<Integer> getDeliveryOfficeRoundIds() {
            return deliveryOfficeRoundIds;
        }

        public void setDeliveryOfficeRoundIds(List<Integer> deliveryOfficeRoundIds) {
            this.deliveryOfficeRoundIds = deliveryOfficeRoundIds;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public static class Geo {
        private String featureType;
        private Address.Geocoord coord1;
        private Address.Geocoord coord2;

        private BigDecimal radiusKm;

        public String getFeatureType() {
            return featureType;
        }

        public void setFeatureType(String featureType) {
            this.featureType = featureType;
        }

        public Address.Geocoord getCoord1() {
            return coord1;
        }

        public void setCoord1(Address.Geocoord coord) {
            this.coord1 = coord;
        }

        public Geo putCoord1(Address.Geocoord coord) {
            this.coord1 = coord;
            return this;
        }

        public Address.Geocoord getCoord2() {
            return coord2;
        }

        public void setCoord2(Address.Geocoord coord2) {
            this.coord2 = coord2;
        }

        public Geo putCoord2(Address.Geocoord coord2) {
            this.coord2 = coord2;
            return this;
        }

        public BigDecimal getRadiusKm() {
            return radiusKm;
        }

        public void setRadiusKm(BigDecimal radiusKm) {
            this.radiusKm = radiusKm;
        }

        public Geo putRadiusKm(BigDecimal radiusKm) {
            this.radiusKm = radiusKm;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public static class Detail {
        private boolean unstructured = true;
        private boolean semiStructured;
        private boolean structured;
        private boolean absGeoData;
        private boolean geo;
        private boolean delivery;
        private boolean facility;
        private boolean startrack;
        private boolean surrounding;

        public boolean isUnstructured() {
            return unstructured;
        }

        public void setUnstructured(boolean unstructured) {
            this.unstructured = unstructured;
        }

        public Detail putUnstructured(boolean unstructured) {
            this.unstructured = unstructured;
            return this;
        }

        public boolean isSemiStructured() {
            return semiStructured;
        }

        public void setSemiStructured(boolean semiStructured) {
            this.semiStructured = semiStructured;
        }

        public Detail putSemiStructured(boolean semiStructured) {
            this.semiStructured = semiStructured;
            return this;
        }

        public boolean isStructured() {
            return structured;
        }

        public void setStructured(boolean structured) {
            this.structured = structured;
        }

        public Detail putStructured(boolean structured) {
            this.structured = structured;
            return this;
        }

        public boolean isAbsGeoData() {
            return absGeoData;
        }

        public void setAbsGeoData(boolean absGeoData) {
            this.absGeoData = absGeoData;
        }

        public Detail putAbsGeoData(boolean absGeoData) {
            this.absGeoData = absGeoData;
            return this;
        }

        public boolean isGeo() {
            return geo;
        }

        public void setGeo(boolean geo) {
            this.geo = geo;
        }

        public Detail putGeo(boolean geo) {
            this.geo = geo;
            return this;
        }

        public boolean isDelivery() {
            return delivery;
        }

        public void setDelivery(boolean delivery) {
            this.delivery = delivery;
        }

        public Detail putDelivery(boolean delivery) {
            this.delivery = delivery;
            return this;
        }

        public boolean isFacility() {
            return facility;
        }

        public void setFacility(boolean facility) {
            this.facility = facility;
        }

        public Detail putFacility(boolean facility) {
            this.facility = facility;
            return this;
        }

        public boolean isStartrack() {
            return startrack;
        }

        public void setStartrack(boolean startrack) {
            this.startrack = startrack;
        }

        public Detail putStartrack(boolean startrack) {
            this.startrack = startrack;
            return this;
        }

        public boolean isSurrounding() {
            return surrounding;
        }

        public void setSurrounding(boolean surrounding) {
            this.surrounding = surrounding;
        }

        public Detail putSurrounding(boolean surrounding) {
            this.surrounding = surrounding;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
