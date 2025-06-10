package com.tartur.capco.domain.model.client;

public class CompanyClient extends Client {
    private final Siren siren;
    /**
     * raison sociale de l'entreprise
     */
    private String legalName;
    private IntracomVATNumber intracomVATNumber;
    private double revenue;

    public CompanyClient(int id, Siren siren, String legalName, double revenue) {
        this(id, siren, legalName, revenue, null);
    }

    public CompanyClient(int id, Siren siren, String legalName, double revenue, IntracomVATNumber numeroTvaIntracom) {
        super(id);
        this.siren = siren;
        this.legalName = legalName;
        this.intracomVATNumber = numeroTvaIntracom;
        this.revenue = revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setIntracomVATNumber(IntracomVATNumber intracomVATNumber) {
        this.intracomVATNumber = intracomVATNumber;
    }

    public IntracomVATNumber getIntracomVATNumber() {
        return intracomVATNumber;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalName() {
        return legalName;
    }

    public Siren getSiren() {
        return siren;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + getId() + '\'' +
                ", siren='" + siren + '\'' +
                ", legalName='" + legalName + '\'' +
                ", intracomVATNumber='" + intracomVATNumber + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}
