package stepdefinition.pojo;

import java.util.HashMap;
import java.util.Map;


public class PlaceOrderPojo {

        private Integer id;
        private Integer petId;


        private Integer quantity;
        private String shipDate;
        private String status;
        private Boolean complete;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();



        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPetId() {
            return petId;
        }

        public void setPetId(Integer petId) {
            this.petId = petId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getShipDate() {
            return shipDate;
        }

        public void setShipDate(String shipDate) {
            this.shipDate = shipDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Boolean getComplete() {
            return complete;
        }

        public void setComplete(Boolean complete) {
            this.complete = complete;
        }


        public Map<String, Object> getAdditionalProperties() {

            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {

            this.additionalProperties.put(name, value);
        }

    }

