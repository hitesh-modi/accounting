package com.prompt.marginplus.services;

import com.prompt.marginplus.models.Invoice;
import com.prompt.marginplus.repositories.CreditNoteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Hitesh Modi on 08-04-2018.
 */
@Service("creditNoteService")
public class CreditNoteService implements ICreditService {

    @Resource(name = "creditNoteRepo")
    private CreditNoteRepository creditNoteRepo;

    @Override
    public long saveCreditNote(Invoice invoice) {
        return 0;
    }
}
