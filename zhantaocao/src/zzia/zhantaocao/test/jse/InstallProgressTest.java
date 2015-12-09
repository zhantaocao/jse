package zzia.zhantaocao.test.jse;

import zzia.zhantaocao.jse.InstallProgress;

public class InstallProgressTest {
	public static void main(String[] args) throws Exception {
		new InstallProgress("XXX程序开始安装，请稍后", "XXX程序正在安装", ">>").install(10) ;
	}
}
