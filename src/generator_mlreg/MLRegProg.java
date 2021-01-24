package generator_mlreg;

import java.util.*;

public class MLRegProg {

	private String datas;
	private List<String> colsPred;
	private List<String> colsTarg;
	
	private String targetLanguage;
	
	private String evaluation;
	
	private String algo;
	
	private String calcul;

	private List<String> linesCode = new ArrayList<String>();
	
	public MLRegProg(String datas, List<String> colsPred, List<String> colsTarg, String targetLanguage, String evaluation, String algo, String calcul) {
		this.datas = datas;
		this.colsPred = colsPred;
		this.colsTarg = colsTarg;
		this.targetLanguage = targetLanguage;
		this.evaluation = evaluation;
		this.algo = algo;
		this.calcul = calcul;
		
		this.generate();
	}

	public String getCode() {
		String code = "";
		for (String line  : this.linesCode) {
			code += line + ";\n";
		}
		return code;
	}
	
	private void generate() {
		this.linesCode.add("target_language : " + this.targetLanguage);
		this.linesCode.add("import \"" + this.datas + "\"");
		this.linesCode.add("predictive_vars : " + this.getCodeCols(this.colsPred));
		this.linesCode.add("target_vars : " + this.getCodeCols(this.colsTarg));
		this.linesCode.add(this.evaluation);
		this.linesCode.add("algorithm : " + this.algo);
		this.linesCode.add("calculate : " + this.calcul);
		this.linesCode.add("loop : 10");
	}
	
	private String getCodeCols(List<String> cols) {
		String codeCols = "";
		if (cols.size() > 0) {
			for (String col : cols) {
				codeCols += "\"" + col + "\",";
			}
			return codeCols.substring(0, codeCols.length() - 1);
		}
		
		return "";
	}
	
	
}
