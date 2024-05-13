from django.shortcuts import render

from transformers import pipeline,AutoConfig, AutoTokenizer 
import pandas as pd

model_name = "beomi/kcgpt2"
config = AutoConfig.from_pretrained(model_name)
tokenizer = AutoTokenizer.from_pretrained(model_name)
tokenizer.pad_token = tokenizer.eos_token
model_path = "C:/Users/kjeng/Desktop/Study/Capstone/DeployML/DeployML/ConvertModel/"
nlg_pipeline = pipeline('text-generation',model=model_path, tokenizer=model_name)

def filter_generated_result(text):
      return text.split("[SEP]")[0]
  
def generate_text(pipe, text, target_style, num_return_sequences=5):
  text = f"{text}[{target_style}]"
  out = pipe(text, num_return_sequences=num_return_sequences,
             pad_token_id=tokenizer.eos_token_id,
             max_length=100)
  return [filter_generated_result(x['generated_text']) for x in out]

# Create your views here.
def predictor(request):
    return render(request, 'MLindex.html')

def formInfo(request):
    src_text = request.GET['sentence']
    style = "Naeng"
    changed = generate_text(nlg_pipeline, src_text, style, num_return_sequences=1)[0]
    print(src_text, style, changed)
    return render(request, "MLresponse.html",{'sentence' : changed})


