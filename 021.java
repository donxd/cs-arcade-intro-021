boolean isIPv4Address(String inputString) {
    Integer [] posicionesSeparacion = getPosicionesSeparacion( inputString );
    int numeroSeparaciones = getNumeroSeparaciones( posicionesSeparacion );

    boolean resultado = false;

    if ( numeroSeparaciones == 3 ){
        resultado = validaGrupoNumeros( inputString, posicionesSeparacion );
    }

    return resultado;
}

Integer [] getPosicionesSeparacion ( String entrada ){
    Integer [] posicionesSeparaciones = new Integer [ 4 ];

    int posicionRecorrido = 0;
    int posicionSeparacion = 0;

    do {
        posicionRecorrido = entrada.indexOf( ".", posicionRecorrido );

        if ( posicionRecorrido != -1 ){
            posicionesSeparaciones[ posicionSeparacion++ ] = posicionRecorrido;
            posicionRecorrido++;
        }

    } while( posicionRecorrido != -1 && posicionSeparacion < 3 );

    return posicionesSeparaciones;
}

int getNumeroSeparaciones ( Integer [] posiciones ){
    int numeroSeparaciones = 0;

    for ( int i = 0; i < posiciones.length; i++ ){
        Integer posicion = posiciones[ i ];
        if ( posicion != null ){
            numeroSeparaciones++;
        }
    }

    return numeroSeparaciones;
}

boolean validaGrupoNumeros ( String entrada, Integer [] posicionesSeparacion ){
    Integer valorGrupo1 = getValorGrupo( entrada, posicionesSeparacion, 0 );
    Integer valorGrupo2 = getValorGrupo( entrada, posicionesSeparacion, 1 );
    Integer valorGrupo3 = getValorGrupo( entrada, posicionesSeparacion, 2 );
    Integer valorGrupo4 = getValorGrupo( entrada, posicionesSeparacion, 3 );

    int limiteNumero = 256;

    return ( valorGrupo1 != null && valorGrupo1 < limiteNumero ) && 
                ( valorGrupo2 != null && valorGrupo2 < limiteNumero ) && 
                    ( valorGrupo3 != null && valorGrupo3 < limiteNumero ) &&
                        ( valorGrupo4 != null && valorGrupo4 < limiteNumero );
}

Integer getValorGrupo ( String entrada, Integer [] posicionesSeparacion, int numeroGrupo ){
    Integer valorGrupo = null;

    int inicioGrupo = 0;
    int finGrupo = 0;
    String fragmentoValor;

    if ( (numeroGrupo + 1) < 4) {

        finGrupo = posicionesSeparacion[numeroGrupo];

        if ( numeroGrupo > 0 ){
            inicioGrupo = posicionesSeparacion[numeroGrupo - 1] + 1;
        }

        fragmentoValor = entrada.substring(inicioGrupo, finGrupo);
    } else {
        inicioGrupo = posicionesSeparacion[numeroGrupo - 1] + 1;
        fragmentoValor = entrada.substring( inicioGrupo, entrada.length() );
    }

    try {
        valorGrupo = Integer.parseInt( fragmentoValor );
    } catch ( NumberFormatException error ){
    }

    return valorGrupo;
}