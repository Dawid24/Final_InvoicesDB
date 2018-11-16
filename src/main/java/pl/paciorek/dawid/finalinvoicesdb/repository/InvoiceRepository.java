package pl.paciorek.dawid.finalinvoicesdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.paciorek.dawid.finalinvoicesdb.model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
