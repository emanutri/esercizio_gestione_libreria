package it.libreria.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

//nel nome della classe vi è Articolo in quanto è una classe specifica
public class UtilityLibroForm {

	public static boolean validateInput(String titoloInputParam, String genereInputParam,
			String pagineStringParam, String dataPubblicazioneStringParam) {
		// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(titoloInputParam) || StringUtils.isBlank(genereInputParam)
				|| !NumberUtils.isCreatable(pagineStringParam) || StringUtils.isBlank(dataPubblicazioneStringParam)) {
			return false;
		}
		return true;
	}

	public static Date parseDatePubblicazioneFromString(String dataPubblicazioneStringParam) {
		if (StringUtils.isBlank(dataPubblicazioneStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataPubblicazioneStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
