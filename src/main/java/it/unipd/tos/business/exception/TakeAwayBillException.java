////////////////////////////////////////////////////////////////////
// [MICHELE] [BALDISSERI] [1193109]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TakeAwayBillException(String msg) {
        super(msg);
    }
}
