package com.pro.exemplos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContainerInfo {

	private String Command;
	private String CreatedAt;
	private String ID;
	private String Image;
	private String Labels;
	private String LocalVolumes;
	private String Mounts;
	private String Names;
	private String Networks;
	private String Platform;
	private String Ports;
	private String RunningFor;
	private String Size;
	private String State;
	private String Status;
}
