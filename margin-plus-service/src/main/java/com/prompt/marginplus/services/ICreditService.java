package com.prompt.marginplus.services;

import com.prompt.marginplus.models.Invoice;

/**
 * Created by Hitesh Modi on 08-04-2018.
 */
public interface ICreditService {

    public long saveCreditNote(Invoice invoice);

}
