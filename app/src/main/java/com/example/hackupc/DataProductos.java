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
            default:
                return new ArrayList<>();
        }
    }
    public static ArrayList<Producto> MICROWAVE_DATA() {
        ArrayList<Producto> arrayList = new ArrayList<>();
        arrayList.add(new Producto("WOLTU 1800W", "75,99 €", "MICROWAVE", "https://www.amazon.es/WOLTU-El%C3%A9ctrico-Conveccci%C3%B3n-Temperatura-Multifunci%C3%B3n/dp/B07NKY17SQ/ref=sr_1_11_sspa?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916482&sr=8-11-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExQlhKUDE1UVpRVUFSJmVuY3J5cHRlZElkPUEwMzIzNDkwMTZRRkVDSkREN0VGRCZlbmNyeXB0ZWRBZElkPUEwODY0NzY0N1Y5UzRVMVpLMEJSJndpZGdldE5hbWU9c3BfbXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ=="));
        arrayList.add(new Producto("Moulinex Optimo OX484810", "119,25 €" ,"MICROWAVE", "https://www.amazon.es/Moulinex-Optimo-OX4848-conveccci%C3%B3n-temporizador/dp/B06XGTZND6/ref=sr_1_13?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=horno&qid=1570916513&sr=8-13"));
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
        return arrayList;
    }
}
