package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;
import java.util.Objects;

public class UmowaZlecenie extends Umowa {
    public static String symbol = "Z";
    private double zaliczkaUS = 0;
    private double zaliczkaUSZaokr = 0;
    private double podstawaOpodat = 0;
    private double podstawaOpodatZaokr = 0;
    private double SkladkaZdrowotna2 = 0;
    private double podatekPotracony = 0;
    private double wynagrodzenie = 0;

    public UmowaZlecenie(double podstawa) {
        super(podstawa);
    }

    public void obliczKoszty() {
        oPodstawa = obliczonaPodstawa(podstawa);
        obliczUbezpieczenia(oPodstawa);
        DecimalFormat df = new DecimalFormat("#");
        kosztyUzyskania = (oPodstawa * 20) / 100;
        podstawaOpodat = oPodstawa - kosztyUzyskania;
        podstawaOpodatZaokr = Double.parseDouble(df.format(podstawaOpodat));
        zaliczkaNaPodatekDochodowy = obliczPodatek(podstawaOpodatZaokr);
        podatekPotracony = zaliczkaNaPodatekDochodowy;
        zaliczkaUS = obliczZaliczke();
        zaliczkaUSZaokr = Double.parseDouble(df.format(zaliczkaUS));
        wynagrodzenie = podstawa - ((SkladkaEmerytalna + SkladkaRentowa + UbezpChorobowe) + SkladkaZdrowotna1 + zaliczkaUSZaokr);

    }

    public void wypiszKoszty() {
        DecimalFormat df00 = new DecimalFormat("#.00");
        DecimalFormat df = new DecimalFormat("#");

        System.out.println("UMOWA-ZLECENIE");
        wypiszSkladki();
        System.out.println("Koszty uzyskania przychodu (stałe) " + kosztyUzyskania);
        System.out.println("Podstawa opodatkowania " + podstawaOpodat + " zaokrąglona " + df.format(podstawaOpodatZaokr));
        System.out.println("Zaliczka na podatek dochodowy 18 % = " + zaliczkaNaPodatekDochodowy);
        System.out.println("Podatek potrącony = " + df00.format(podatekPotracony));
        System.out.println("Zaliczka do urzędu skarbowego = "
                + df00.format(zaliczkaUS) + " po zaokrągleniu = "
                + df.format(zaliczkaUSZaokr));
        System.out.println();
        System.out.println("Pracownik otrzyma wynagrodzenie netto w wysokości = " + df00.format(wynagrodzenie));
    }

    public double obliczZaliczke() {
        kwotaZmiejszajacaPodatek = 0;
        return zaliczkaNaPodatekDochodowy - SkladkaZdrowotna2 - kwotaZmiejszajacaPodatek;
    }

    public void obliczUbezpieczenia(double podstawa) {
        SkladkaZdrowotna1 = (podstawa * 9) / 100;
        SkladkaZdrowotna2 = (podstawa * 7.75) / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmowaZlecenie that = (UmowaZlecenie) o;
        return Double.compare(that.zaliczkaUS, zaliczkaUS) == 0 &&
                Double.compare(that.zaliczkaUSZaokr, zaliczkaUSZaokr) == 0 &&
                Double.compare(that.podstawaOpodat, podstawaOpodat) == 0 &&
                Double.compare(that.podstawaOpodatZaokr, podstawaOpodatZaokr) == 0 &&
                Double.compare(that.SkladkaZdrowotna2, SkladkaZdrowotna2) == 0 &&
                Double.compare(that.podatekPotracony, podatekPotracony) == 0 &&
                Double.compare(that.wynagrodzenie, wynagrodzenie) == 0 &&
                Double.compare(that.kosztyUzyskania, kosztyUzyskania) == 0 &&
                Double.compare(that.zaliczkaNaPodatekDochodowy, zaliczkaNaPodatekDochodowy) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zaliczkaUS, zaliczkaUSZaokr, podstawaOpodat, podstawaOpodatZaokr, SkladkaZdrowotna2, podatekPotracony, wynagrodzenie, kosztyUzyskania, zaliczkaNaPodatekDochodowy);
    }
}