package com.example.hackupc;

import java.util.ArrayList;

public class DataProductos {
    /**
     * Clase del HardCoding. Se usa a modo base de datos de productos.
     */

    public static ArrayList<Producto> ReturnData(String type) {
        switch (type) {
            case "microwave":
                return MICROWAVE_DATA();
            case "refrigerator":
                return REFRIGERATOR_DATA();
            case "oven":
                return OVEN_DATA();
            case "dishwasher":
                return DISH_WASHER_DATA();
            default:
                return new ArrayList<>();
        }
    }
    public static ArrayList<Producto> MICROWAVE_DATA() {
        ArrayList<Producto> arrayList = new ArrayList<>();
        arrayList.add(new Producto("WOLTU 1800W", "75,99 €", "MICROWAVE", "https://www.amazon.es/WOLTU-El%C3%A9ctrico-Conveccci%C3%B3n-Temperatura-Multifunci%C3%B3n/dp/B07NKY17SQ/ref=sr_1_11_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916482&sr=8-11-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExQlhKUDE1UVpRVUFSJmVuY3J5cHRlZElkPUEwMzIzNDkwMTZRRkVDSkREN0VGRCZlbmNyeXB0ZWRBZElkPUEwODY0NzY0N1Y5UzRVMVpLMEJSJndpZGdldE5hbWU9c3BfbXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ=="));
        arrayList.add(new Producto("Moulinex Optimo OX484810", "119,25 €" ,"MICROWAVE", "https://www.amazon.es/Moulinex-Optimo-OX4848-conveccci%C3%B3n-temporizador/dp/B06XGTZND6/ref=sr_1_13?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916513&sr=8-13"));
        arrayList.add(new Producto("Moulinex Optimo OX484810", "119,25 €" ,"MICROWAVE", "https://www.amazon.es/Moulinex-Optimo-OX4848-conveccci%C3%B3n-temporizador/dp/B06XGTZND6/ref=sr_1_13?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916513&sr=8-13"));
        arrayList.add(new Producto("Moulinex Optimo OX484810", "119,25 €" ,"MICROWAVE", "https://www.amazon.es/Moulinex-Optimo-OX4848-conveccci%C3%B3n-temporizador/dp/B06XGTZND6/ref=sr_1_13?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916513&sr=8-13"));
        arrayList.add(new Producto("MicroDummy 124", "25.00 €" ,"MICROWAVE", "lleidahack.github.io"));
        arrayList.add(new Producto("Orbegozo MI", "48 €" ,"MICROWAVE", "https://www.amazon.es/Orbegozo-MI-2015-Microondas-funcionamiento/dp/B00KB6BE4E/ref=sr_1_3?__mk_es_ES=ÅMÅŽÕÑ&keywords=microondas&qid=1570949011&sr=8-3"));
        arrayList.add(new Producto("Teka MW255", "12€" ,"MICROWAVE", "https://www.amazon.es/Teka-MW-225-Microondas-litros/dp/B011EOWGRK/ref=sr_1_6?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=microondas&qid=1570949187&sr=8-6"));
        arrayList.add(new Producto("Cecotec Sencillo", "55€" ,"MICROWAVE", "https://www.amazon.es/Cecotec-All-Black-Microondas-frontal/dp/B06Y34SXKS/ref=sr_1_5?__mk_es_ES=ÅMÅŽÕÑ&keywords=microondas&qid=1570949187&sr=8-5"));
        arrayList.add(new Producto("Daewoo KOG", "88,42€" ,"MICROWAVE", "https://www.amazon.es/Daewoo-KOG-A8B5R-Microondas-litros-inoxidable/dp/B0752TNSB5/ref=sr_1_8?__mk_es_ES=ÅMÅŽÕÑ&keywords=microondas&qid=1570949187&sr=8-8"));
        arrayList.add(new Producto("Cocotec ProClean", "54,90€" ,"MICROWAVE", "https://www.amazon.es/Cecotec-ProClean-3050-Revestimiento-Ready2Clean/dp/B07JZD2KGH/ref=sr_1_12_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=microondas&qid=1570949187&sr=8-12-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzUzFDVFpZUDVWUkpZJmVuY3J5cHRlZElkPUEwNjYxMjI4MklWV0tNTkcyVEoyVyZlbmNyeXB0ZWRBZElkPUEwODg4MjQ1MjdTQ0Q0RVhUMDhWViZ3aWRnZXROYW1lPXNwX210ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU="));
        arrayList.add(new Producto("Teka MWE225", "80,90€" ,"MICROWAVE", "https://www.amazon.es/Teka-MWE-225-Microondas-grill/dp/B010CT4UZY/ref=sr_1_14?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=microondas&qid=1570949187&sr=8-14"));


        return arrayList;

    }
    public static ArrayList<Producto> REFRIGERATOR_DATA() {
        ArrayList<Producto> arrayList = new ArrayList<>();
        arrayList.add(new Producto("Beko TS 190320\n","142,00 €\n", "REFRIGERATOR", "https://www.amazon.es/Beko-190320-Frigor%C3%ADfico-Compartimento-Congelador/dp/B007XAPN1G/ref=sr_1_1?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=neveras&qid=1570916347&sr=8-1\n"));
        arrayList.add(new Producto("Daewoo RN-BH360\n", "376,75 €\n", "REFRIGERATOR", "https://www.amazon.es/Daewoo-RN-BH360NPT-Independiente-congelador-inoxidable/dp/B07CNGK6FP/ref=sr_1_2?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=neveras&qid=1570916389&sr=8-2\n"));
        return arrayList;

    }
    public static ArrayList<Producto> OVEN_DATA() {
        ArrayList<Producto> arrayList = new ArrayList<>();
        arrayList.add(new Producto("Sauber\n", "399,99 €", "OVEN", "https://www.amazon.es/Sauber-Pirol%C3%ADtico-Multifunci%C3%B3n-Eficiencia-Energ%C3%A9tica/dp/B07NSLY1R9/ref=sr_1_1_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916450&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzMU80WUlRWUNBNEFHJmVuY3J5cHRlZElkPUEwODcyNTY0UVdUVVpWQk9LSENQJmVuY3J5cHRlZEFkSWQ9QTEwMDA0OTQzR0RCSUkyNjhKV1BGJndpZGdldE5hbWU9c3BfYXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ==\n"));
        arrayList.add(new Producto("Candy\n", "146,00 €\n", "OVEN", "https://www.amazon.es/Candy-El%C3%A9ctrico-Litros-Cristal-Inoxidable/dp/B073FQ4HT9/ref=sr_1_4?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916466&sr=8-4\n"));
        arrayList.add(new Producto("Horno SHMP01I", "399€" ,"oven", "https://www.amazon.es/Sauber-Pirol%C3%ADtico-Multifunci%C3%B3n-Eficiencia-Energ%C3%A9tica/dp/B07NSLY1R9/ref=sr_1_1_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570949585&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUFURDUwUEpKMUpGMEwmZW5jcnlwdGVkSWQ9QTAxODAyNTYxRkM2WlFSRkpRSExDJmVuY3J5cHRlZEFkSWQ9QTEwMDA0OTQzR0RCSUkyNjhKV1BGJndpZGdldE5hbWU9c3BfYXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ=="));
        arrayList.add(new Producto("Horno multifunción SHM03B", "299€" ,"oven", "https://www.amazon.es/Sauber-multifunci%C3%B3n-SHM03B-Eficiencia-Energ%C3%A9tica/dp/B07Q3XTKV5/ref=sr_1_2_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570949585&sr=8-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUFURDUwUEpKMUpGMEwmZW5jcnlwdGVkSWQ9QTAxODAyNTYxRkM2WlFSRkpRSExDJmVuY3J5cHRlZEFkSWQ9QTA5OTk5NzAxOVZHTURKUEc4SUtPJndpZGdldE5hbWU9c3BfYXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ=="));
        arrayList.add(new Producto("Candy - FCS100X", "146,18€" ,"oven", "https://www.amazon.es/Candy-El%C3%A9ctrico-Litros-Cristal-Inoxidable/dp/B073FQ4HT9/ref=sr_1_3?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570949585&sr=8-3"));
        arrayList.add(new Producto("Balay 3HB4331X0 ", "277€" ,"oven", "https://www.amazon.es/Balay-3HB4331X0-Horno-Medio-el%C3%A9ctrico/dp/B07BZYFQFF/ref=sr_1_8?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570949585&sr=8-8"));
        arrayList.add(new Producto("Candy FCS 100", "148,30€" ,"oven", "https://www.amazon.es/Candy-FCS-100-el%C3%A9ctrico-Giratorio/dp/B072R3P5NJ/ref=sr_1_10?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570949585&sr=8-10"));
        arrayList.add(new Producto("EB8015ED - KKT KOLBE", "464€" ,"oven", "https://www.amazon.es/construido-temporizador-convecci%C3%B3n-KKT-KOLBE/dp/B00OZR8N8A/ref=sr_1_11_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570949585&sr=8-11-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUFURDUwUEpKMUpGMEwmZW5jcnlwdGVkSWQ9QTAxODAyNTYxRkM2WlFSRkpRSExDJmVuY3J5cHRlZEFkSWQ9QTAzMDI4MTlXNENBMDJEV040Rlkmd2lkZ2V0TmFtZT1zcF9tdGYmYWN0aW9uPWNsaWNrUmVkaXJlY3QmZG9Ob3RMb2dDbGljaz10cnVl"));


        return arrayList;
    }
    public static ArrayList<Producto> DISH_WASHER_DATA() {
        ArrayList<Producto> arrayList = new ArrayList<>();

        arrayList.add(new Producto("Indesit DFG 15B", "264€" ,"dishwasher", "https://www.amazon.es/Indesit-DFG-15B-independiente-temperaturas/dp/B00H9YLEXW/ref=sr_1_1?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=dishwasher&qid=1570949402&sr=8-1"));
        arrayList.add(new Producto("Bosch - Lavavajillas 48Db", "340€" ,"dishwasher", "https://www.amazon.es/Bosch-Lavavajillas-48-color-blanco/dp/B00UV8V2B2/ref=sr_1_2?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=dishwasher&qid=1570949402&sr=8-2"));
        arrayList.add(new Producto("Klarstein Amazonia 6", "259€" ,"dishwasher", "https://www.amazon.es/Klarstein-lavavajillas-cubiertos-programas-programable/dp/B076CH7DX3/ref=sr_1_3?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=dishwasher&qid=1570949402&sr=8-3"));
        arrayList.add(new Producto("Bosch Serie 2 SMS25AW05E", "352€" ,"dishwasher", "https://www.amazon.es/Bosch-SMS25AW05E-Independiente-12cubiertos-lavavajilla/dp/B06XNMZQCV/ref=sr_1_4?__mk_es_ES=ÅMÅŽÕÑ&keywords=dishwasher&qid=1570949402&sr=8-4"));
        arrayList.add(new Producto("Bosch Serie 4 SMS46II08E", "479€" ,"dishwasher", "https://www.amazon.es/Bosch-SMS46II08E-Independiente-13cubiertos-lavavajilla/dp/B06XKXPB1V/ref=sr_1_5?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=dishwasher&qid=1570949402&sr=8-5"));
        return arrayList;


    }
}
