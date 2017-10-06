package au.com.auspost.voicesort.web.value;

public class AddressVO {

    private String address;
    private Integer roundId;
    private String confidence;
//    private dpid;
//    private deliveryOfficeRoundId;
//    private section;
//    private dpidSequenceNumber;
//    private stem;
//        private     latitude;
//        private           longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AddressVO putAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public AddressVO putRoundId(Integer roundId) {
        this.roundId = roundId;
        return this;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public AddressVO putConfidence(String confidence) {
        this.confidence = confidence;
        return this;
    }
}
