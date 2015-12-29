  function cdss($input){
                var json_input = $input;
		var lesions = [];
		lesions.push({key:'atrophic',value:'atrophic'});
		lesions.push({key:'bulla',value:'bullae'});
		lesions.push({key:'crusting',value:'crusting'});
		lesions.push({key:'depigmented',value:'depigmented'});
		lesions.push({key:'erosion',value:'erosion'});
		lesions.push({key:'excoriation',value:'excoriation'});
		lesions.push({key:'fissure',value:'fissure'});
		lesions.push({key:'lichenification',value:'lichenification'});
		lesions.push({key:'ulcer',value:'ulcer'});
		lesions.push({key:'vesicle',value:'vesicle'});
		lesions.push({key:'macule',value:'macule'});
		lesions.push({key:'nodule',value:'nodule'});
		lesions.push({key:'papule',value:'papule'});
		lesions.push({key:'hyperpigmented',value:'hyperpigmented'});
		lesions.push({key:'hypopigmented',value:'hypopigmented'});
		lesions.push({key:'central',value:'central'});
		lesions.push({key:'centrifugal',value:'centrifugal'});
		lesions.push({key:'koebner',value:'linear'});
		lesions.push({key:'photosensitive',value:'photosensitive'});
		lesions.push({key:'plaque',value:'plaque'});
		lesions.push({key:'purpura',value:'purpuric'});
		lesions.push({key:'pustule',value:'pustule'});
		lesions.push({key:'scaling',value:'scaling'});
		lesions.push({key:'scar',value:'scar'});

		lesions.push({key:'itch',value:'itch'});
		lesions.push({key:'fever',value:'fever'});
		lesions.push({key:'burning',value:'burning'});
		lesions.push({key:'male',value:'male'});
		lesions.push({key:'female',value:'female'});
		lesions.push({key:'face',value:'face'});
		lesions.push({key:'hands',value:'hands'});
		lesions.push({key:'legs',value:'legs'});
		lesions.push({key:'groin',value:'groin'});
		lesions.push({key:'mouth',value:'mouth'});
		lesions.push({key:'lips',value:'lips'});
                
                var cdss_input;
                var i;
		for(i=0;i<lesions.length;i++){
                        if (json_input.indexOf(lesions[i].key) > -1) {
                          //code
                          cdss_input = cdss_input + ' ' + lesions[i].value;
                        }
		}
                return cdss_input;
  }