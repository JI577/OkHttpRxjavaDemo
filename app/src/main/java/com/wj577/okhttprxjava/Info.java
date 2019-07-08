package com.wj577.okhttprxjava;

import java.util.List;

public class Info {


    /**
     * reason : 查询成功
     * result : ["AMNAT CHAROEN","ANG THONG","BANGKOK","BUENG KAN","BURI RAM","CHACHOENGSAO","CHAI NAT","CHAIYAPHUM","CHANTHABURI","CHIANG RAI","CHINGMAI","CHOMPHON","CHON BURI","KALASIN","KAMPHAENG PHET","KANCHANABURI","KHON KAEN","KRABI","LAMPANG","LAMPHUN","LOEI","LOP BURI","MAE HONG SON","MAHA SARAKHAM","MUKDAHAN","NAKHON NAYOK","NAKHON PATHOM","NAKHON PHANOM","NAKHON RATCHASIMA","NAKHON SAWAN","NAKHON SI THAMMARAT","NAN","NARATHIWAT","NONG BUA LAM PHU","NONG KHAI","NONTHABURI","PATHUM THANI","PATTANI","PHANG NGA","PHATTHALUNG","PHAYAO","PHETCHABUN","PHETCHABURI","PHICHIT","PHITSANULOK","PHRA NAKHON SI AYUTTHAYA","PHRAE","PHUKET","PRACHIN BURI","PRACHUAP KHIRI KHAN","RANONG","RATCHABURI","RAYONG","ROI ET","SA KAEO","SAKON NAKHON","SAMUT PRAKAN","SAMUT SAKHON","SAMUT SONGKHRAM","SARABURI","SATUN","SI SA KET","SING BURI","SONGKHLA","SUKHOTHAI","SUPHAN BURI","SURAT THANI","SURIN","TAK","TRANG","TRAT","UBON RATCHATHANI","UDON THANI","UTHAI THANI","UTTARADIT","YALA","YASOTHON"]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<String> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
