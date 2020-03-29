import ntpath
import os
import subprocess
import sys
import zipfile



def make_dir(d):
	bp=os.getcwd()
	td=""
	for k in d.replace("\\","/").split("/"):
		if (k=="." or k==""):
			continue
		ot=td+""
		if (len(td)==0):
			td=k
		else:
			td+="/"+k
		if (not ntpath.exists(td)):
			if (ot==""):
				ot="./"
			os.system(f"cd {ot}&&mkdir {k}&&cd {bp}")



def error(cmd,dc=""):
	p=subprocess.run(cmd,stdout=subprocess.PIPE,stderr=subprocess.PIPE)
	if (len(p.stderr)!=0):
		print(str(p.stderr)[2:-1].replace("\\r\\n","\n").replace("\\t","\t").replace("\\'","'").replace("\\\\","\\"))
		os.system(dc)
		quit()



def jar(nm,mp,*dl):
	with zipfile.ZipFile(nm+".jar","w") as zf:
		zf.write(mp,arcname="META-INF/MANIFEST.MF")
		wf=[]
		for d in dl:
			bp=ntpath.abspath(d)+"\\"
			for r,_,fl in os.walk(d):
				for f in fl:
					if (not ntpath.abspath(ntpath.join(r,f)).replace(bp,"") in wf):
						zf.write(ntpath.abspath(ntpath.join(r,f)),arcname=ntpath.abspath(ntpath.join(r,f)).replace(bp,""))
						wf+=[ntpath.abspath(ntpath.join(r,f)).replace(bp,"")]
						print(ntpath.abspath(ntpath.join(r,f)).replace(bp,""))



os.system("rm -rf build&&mkdir build")
error(["javac","-cp","./build/;./jar/;","-d","build","com/krzem/video_editor/*.java"])
for r,_,fl in os.walk("./com/krzem/video_editor/effects/"):
	for f in fl:
		make_dir(f"build/{r}")
		error(["javac","-cp","./build/;./jar/;","-d",f"build/{r}",ntpath.join(r,f)])
with open("./_tmp_m.tmp","w") as f:
	f.write("Manifest-Version: 1.0\nCreated-By: Krzem\nMain-Class: com.krzem.video_editor.Main\n")
jar("video_editor","./_tmp_m.tmp","build","jar")
os.system("del _tmp_m.tmp&&rm -rf build&&mkdir build&&copy video_editor.jar build&&del video_editor.jar&&cls")
os.system("cd build&&java -jar video_editor.jar")
os.system("cd ../")
