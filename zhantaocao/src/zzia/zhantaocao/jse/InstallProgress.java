package zzia.zhantaocao.jse;

import java.util.Scanner;
/**
 * 
 * @author zhantaocao
 *
 */
public class InstallProgress {
	
	private String installInitInfo ;
	private String progressInitInfo ;
	private String progressIcon ;
	
	public InstallProgress() {
		// TODO Auto-generated constructor stub
	}
	
	public InstallProgress(String installInitInfo, String progressInitInfo,
			String progressIcon) {
		super();
		this.installInitInfo = installInitInfo;
		this.progressInitInfo = progressInitInfo;
		this.progressIcon = progressIcon;
	}
	
	public void install(Integer icoNum) throws InterruptedException{
		if(installInitInfo==null
				||progressInitInfo==null
				||progressIcon==null){
			System.out.println("��ʼ��ʧ�ܣ������ʼ������");
		}else{
			System.out.println(installInitInfo);
			Thread.sleep(2000) ;
			System.out.print(progressInitInfo);
			for (int i = 0; i < icoNum; i++) {
				if(i==(icoNum-1)){
					System.out.print("OK");
					Scanner scanner = new Scanner(System.in) ;
					System.out.println("\n"+"XXX����װ�ɹ��������Ƿ���������Y:N?");
					String scanInfoStr = scanner.nextLine() ;
					if("Y".equals(scanInfoStr)){
						System.out.println("����");
					}else if("N".equals(scanInfoStr)){
						System.out.println("�Ǻ�");
					}
				}else{
					System.out.print(progressIcon);
					Thread.sleep(1000) ;
				}
			}
		}
	}
	
}
