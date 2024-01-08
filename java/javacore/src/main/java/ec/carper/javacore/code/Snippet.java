package ec.carper.javacore.code;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Snippet {

    private static String localeLanguage = "es";
    private static String localeCountry = "CL";
    private static String formatoTasaInteres = "#0.0000'%'";
    private static String formatoMontoPorMoneda = "'USD '#,###0.00";

    public static String getLocaleLanguage() {
        return localeLanguage;
    }
    public static String getLocaleCountry() {
        return localeCountry;
    }
	public static String getFormatoTasaInteres() {
        return formatoTasaInteres;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1(){
        //System.out.println(fomatearTasaInteresAnual(BigDecimal.ZERO));
        System.out.println(formatearMonto("USD", BigDecimal.ZERO));
        System.out.println(formatearMonto("USD", new BigDecimal("0.25")));
        System.out.println(formatearMonto("USD", new BigDecimal("5.25")));
        System.out.println(formatearMonto("USD", new BigDecimal("100")));
    }
    
    private static String fomatearTasaInteresAnual(BigDecimal tasaInteres){
        Locale esLocale =new Locale(getLocaleLanguage(), getLocaleCountry());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(esLocale);

        DecimalFormat df = new DecimalFormat(getFormatoTasaInteres(),symbols);
        return df.format(tasaInteres);
    }

    public static String formatearMonto(String moneda, BigDecimal monto){
        Locale esLocale =new Locale(getLocaleLanguage(), getLocaleCountry());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(esLocale);
        
        //DecimalFormat df = new DecimalFormat(notificacionEmailConfiguration.getCorreo().obtenerFormatoMontoSegunMoneda(moneda),symbols);
        DecimalFormat df = new DecimalFormat(formatoMontoPorMoneda, symbols);

        return df.format(monto);
    }

}
