package com.htpe.mapper.nnew;

import com.htpe.bean.Kpi;

public interface KPIMapper {

	Double timeoutCountForBarcode(Kpi kpi);

	Double countForBarcode(Kpi kpi);

}
