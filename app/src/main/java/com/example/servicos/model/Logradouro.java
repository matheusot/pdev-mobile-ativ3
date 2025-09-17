package com.example.servicos.model;

public class Logradouro {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String country;
    private String complement;
    private String ibge;

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getCountry() {
        return country;
    }

    public String getIbge() {
        return ibge;
    }

    public String formatar(String tipo) {
        if (tipo == "CEP") {
            return formatarCEP();
        }
        return formatarIP();
    }

    public String formatarCEP() {
        return this.street
                .concat("\n")
                .concat(this.neighborhood)
                .concat("\n")
                .concat(this.city)
                .concat("\n")
                .concat(this.state);
    }

    public String formatarIP() {
        String city = "";
        String state = "";
        String country = "";

        if (this.city != null) {
            city = this.city;
        }

        if (this.state != null) {
            state = this.state;
        }

        if (this.country != null) {
            country = this.country;
        }

        return city
                .concat("\n")
                .concat(state)
                .concat("\n")
                .concat(country);
    }
}