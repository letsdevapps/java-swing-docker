package com.pro.exemplos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ContainerList {
	List<ContainerInfo> containers;
}
