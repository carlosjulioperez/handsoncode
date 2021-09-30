package ec.carper.javacore.testing;

import java.util.Optional;

public class UserStats {
    
    private Optional<Long> visitCount;

    public UserStats(Optional<Long> visitCount) {
        super();
        this.visitCount = visitCount;
    }

    public Optional<Long> getVisitCount() {
        return this.visitCount;
    }

    // private Long visitCount;

    // public UserStats(Long visitCount) {
    //     this.visitCount = visitCount;
    // }

    // public Long getVisitCount() {
    //     return this.visitCount;
    // }
}
