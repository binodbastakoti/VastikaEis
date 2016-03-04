app.controller('DocumentTypeController',['$scope','$http','baseUrl', '$modal', documentTypeController]);

function documentTypeController($scope,$http,baseUrl,$modal){
	
	$http.get(baseUrl+'api/documentType')
	.then(function(response){
		$scope.documentType=response.data;
	})
	$scope.open=function()
	{
		var modalInstance=$modal.open({
			animation:true,
			templateUrl:'resources/views/documents/add-document-type.html',
			controller:'DocumentTypeModal',
			resolve:{
				documentType:false
			}
		});
		modalInstance.result.then(function(newDocumentType){
			if(newDocumentType){
				$scope.documentType.push(newDocumentType);
			}
		}, function () {
		});
	}
	
	
	
	$scope.edit=function(documentType){
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/documents/add-document-type.html',
			controller : 'DocumentTypeModal',
			resolve: {
				
		        documentType: function() {
		        	return documentType;
		        }
		    }
		});
		
		modalInstance.result.then(function (updateDocumentType) {
			if(updateDocumentType){
				var ref=_.find($scope.documentType,function(el){
					return el.documentTypeId==updateDocumentType.documentTypeId;
				});
				$scope.documentType[_.indexOf($scope.documentType,ref)]=updateDocumentType;
			}

	    });
	}
	
	$scope.delete = function(documentType) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/documentType/'+documentType.documentTypeId)
			.then(function(response) {
				toastr.success(response.data.message, "Success");
				$scope.documentType = _.reject($scope.documentType, function(el) { return el.documentTypeId === documentType.documentTypeId; });
			});
		});		
	}
}

app.controller('DocumentTypeModal',['$scope', '$modalInstance', 'documentType', 'baseUrl', '$http', documentTypeModal]);

function documentTypeModal($scope,$modalInstance,documentType,baseUrl,$http){
	if(documentType){
		$scope.mode="Edit";
		$scope.documentType={description:documentType.description};
		$scope.documentTypeId=documentType.documentTypeId;
	}else {
		$scope.mode="Add";
		$scope.documentType={description:""};	
	}
	
	$modalInstance.rendered.then(function(){
		$("#document-type-description").focus();
	});
	
	$scope.add=function(){
		$scope.$broadcast('show-errors-check-validity');
		if(!$scope.addDocumentTypeForm.$valid){
			toastr.error("Please fill required fields.","Error");
			return;
		}
	
		if($scope.mode=="Add"){
			$scope.processing=true;
			var promise=$http.post(baseUrl+'api/documentType',$scope.documentType);
		}else {
			$scope.processing=true;
			var promise=$http.put(baseUrl+'api/documentType/'+$scope.documentTypeId,$scope.documentType);
		}
		
		promise.then(function(response) {
			if(response.data.status=='success'){
				toastr.success(response.data.message, "Success");
				if($scope.mode=="Add")
					$scope.documentType.documentTypeId=response.data.id;
				else
					$scope.documentType.documentTypeId=$scope.documentTypeId;
				$modalInstance.close($scope.documentType);
			}
			else{
				toastr.error("Something went wrong.","Error");
				$modalInstance.close();
			}
			$scope.processing=false;
			
		}, function() {
			toastr.error("Something went wrong.", "Error");
			$scope.processing=false;
		});
	};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.$on('back-button-pressed', function (event, args) {
		$modalInstance.dismiss('cancel');
	});
	
}

app.controller('HeroesDocumentController', [ '$scope', '$http', 'baseUrl', 'Upload', '$stateParams', '$modal', 'Lightbox', heroesDocumentController]);
                                             
function heroesDocumentController($scope, $http, baseUrl, Upload, $stateParams, $modal, Lightbox) {
	
	$scope.userId = parseInt($stateParams.id, 10);
	
	$http.get(baseUrl + "api/users/" + $scope.userId).then(function(response) {
		$scope.user = response.data;
	});
	
	$http.get(baseUrl + 'api/heroes/' + $scope.userId + '/resumes')
	.then(function(response) {
		$scope.resumes = response.data;
	});
	
	$http.get(baseUrl + 'api/heroes/' + $scope.userId + '/documents')
	.then(function(response) {
		$scope.documents = response.data;
	});
	
	$scope.addResume = function() {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/document/add-resume.html',
			controller : 'ResumeModal',
			resolve: {
		        resume: function() {
		        	return false;
		        },
		        userId: function() {
		        	return $scope.userId;
		        }
		    }
		});
		
		modalInstance.result.then(function (newResume) {
			if(newResume) {
				$scope.resumes.push(newResume);
			}
	    }, function () {
		});
	};
	
	$scope.editResume = function(resume) {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/document/add-resume.html',
			controller : 'ResumeModal',
			resolve: {
		        resume: function() {
		        	return resume;
		        },
		        userId: function() {
		        	return $scope.userId;
		        }
		    }
		});
		
		modalInstance.result.then(function (updatedResume) {
			if(updatedResume) {
				var ref = _.find($scope.resumes, function(el) {
					return el.id == updatedResume.id; 
				});
				$scope.resumes[_.indexOf($scope.resumes, ref)] = updatedResume;
			}
	    });
	}

	$scope.deleteResume = function(resume) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/resumes/'+resume.id)
			.then(function(response) {
				toastr.success(response.data.message, "Success");
				$scope.resumes = _.reject($scope.resumes, function(el) { return el.id === resume.id; });
			});
		});		
	}
	
	$scope.addDocument=function(document){		
		var modalInstance = $modal.open({			
			animation : true,
			templateUrl : 'resources/views/document/add-document.html',
			controller : 'DocumentModal',
			resolve: {
		        document: function() {
		        	return false;
		        },
		        userId: function() {
		        	return $scope.userId;
		        }
		    }
		});
		
		modalInstance.result.then(function (newDocument) {
			if(newDocument) {
				$scope.documents.push(newDocument);
			}
	    }, function () {
		});
	};
	
	$scope.editDocument = function(document) {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/document/add-document.html',
			controller : 'DocumentModal',
			resolve: {
				document: function() {
		        	return document;
		        },
		        userId: function() {
		        	return $scope.userId;
		        }
		    }
		});
		
		modalInstance.result.then(function (updatedDocument) {
			if(updatedDocument) {
				var ref = _.find($scope.documents, function(el) {
					return el.id == updatedDocument.id; 
				});
				$scope.documents[_.indexOf($scope.documents, ref)] = updatedDocument;
			}
	    });
	}

	$scope.deleteDocument = function(document) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/documents/'+document.id)
			.then(function(response) {
				toastr.success(response.data.message, "Success");
				$scope.documents = _.reject($scope.documents, function(el) { return el.id === document.id; });
			});
		});		
	}
	
	$scope.showPreview = function(src) {
		Lightbox.openModal([{url: src}], 0);
	}
}

app.config(function (baseUrl, LightboxProvider) {
	// set a custom template
	LightboxProvider.templateUrl = baseUrl + 'resources/views/bootstrap/lightbox/single.html';
});

app.controller('DocumentModal', ['$scope', '$modalInstance', 'document', 'userId', 'baseUrl', '$http', 'Upload', '$filter', documentModal]);

function documentModal($scope, $modalInstance, document, userId, baseUrl, $http, Upload, $filter) {
	
	var getDocumentTypes = $http.get(baseUrl + "api/documentType");
	
	if(document) {
		$scope.mode = "Edit";
		$scope.document = _.omit(document, ['id', 'documentTypeId', 'fileType']);
		$scope.document.documentType={id:document.documentTypeId};
		$scope.document.expiryDate = $filter('date')($scope.document.expiryDate, "MM/dd/yyyy");
		$scope.id = document.id;
		$scope.userId = userId;	
		getDocumentTypes.then(function(response) {
			$scope.documentTypes = response.data;
			$scope.selectedDocumentType = _.find($scope.documentTypes, function(el){
				return el.documentTypeId == document.documentTypeId; 
			});
		});
	} else {
		$scope.mode = "Add";
		$scope.document = {documentType: {id:""}, description: "", expiryDate: "", fileUpload: {id: ""}};
		$scope.uploadStatus = "NoFileUploaded";
		$scope.userId = userId;
		getDocumentTypes.then(function(response) {
			$scope.documentTypes = response.data;
		});
	}
	
	$modalInstance.rendered.then(function(){
		$("#document-title").focus();
		$('.date-picker').datepicker({});
		
	});
	
	$scope.$watch('file', function(file) {
		if(file) {
			$scope.upload($scope.file);
		}
	});
	

	$scope.upload = function (file) {
        Upload.upload({
            url: baseUrl + 'api/fileUpload',
            fields: {'type': 'document'},
            file: file
        }).progress(function (evt) {
    		$scope.uploadStatus = "Uploading";
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
    		$scope.uploadProgress = progressPercentage;
        }).success(function (data, status, headers, config) {
            $scope.uploadedFile = {name: config.file.name, id: data.id};
            toastr.success("File uploaded successfully.", "Success");
            $scope.document.fileUpload.id = data.id;            
    		$scope.uploadStatus = "Uploaded";
    		$scope.fileType = data.fileType;
        }).error(function (data, status, headers, config) {
            toastr.error("Something went wrong", "Error");
    		$scope.uploadStatus = "NoFileUploaded";
        })
    };
    
	$scope.addDocument = function() {
		
		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addDocumentForm.$valid) {
			toastr.error("Please fill required fields.", "Error");
			return;
		}
		
		if($scope.document.expiryDate.contains('/')) {
			var expiryDate = $scope.document.expiryDate.split('/');
			$scope.document.expiryDate = expiryDate[2] + '-' + expiryDate[0] + '-' + expiryDate[1];
		}
		  
		$scope.document.documentType.id=$scope.selectedDocumentType.documentTypeId;
		
		if($scope.mode == "Add") {
			var promise = $http.post(baseUrl + 'api/heroes/' + $scope.userId + '/documents', $scope.document); 
		} else {
			var promise = $http.put(baseUrl + 'api/documents/' + $scope.id, $scope.document);
		}
		
		promise.then(function(response) {
			if(response.data.status == "success") {
				toastr.success(response.data.message, "Success");
				if($scope.mode == "Add") {
					var newDocument = _.omit($scope.document,"fileUpload");
					newDocument.documentType=$scope.selectedDocumentType.description;
					newDocument.documentTypeId=$scope.selectedDocumentType.documentTypeId;
					newDocument.id = response.data.id;
					newDocument.fileType=$scope.fileType;	
					$modalInstance.close(newDocument);
				} else {
					$scope.document.id = $scope.id;
					$scope.document.documentType=$scope.selectedDocumentType.description;
					$scope.document.documentTypeId=$scope.selectedDocumentType.documentTypeId;	
					$scope.document.fileType=document.fileType;		
					$modalInstance.close($scope.document);
				}				
				
			} else {
				toastr.error("Something went wrong.", "Error");
			}
		}, function() {
			toastr.error("Something went wrong.", "Error");
		});
	};
	

	$scope.deleteFile = function(uploadedFileId) {
		$http.delete(baseUrl + 'api/fileUpload/' + uploadedFileId)
			.then(function(response) {
				if(response.data.status == "success") {
					toastr.success(response.data.message, "Success");
		            $scope.uploadedFile = {name: "", id: ""};
		            $scope.document.fileUpload.id = "";            
		    		$scope.uploadStatus = "NoFileUploaded";
				} else {
					toastr.error("Something went wrong.", "Error");
				}
		}, function() {
			toastr.error("Something went wrong.", "Error");
		});
	}
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}

app.controller('ResumeModal', ['$scope', '$modalInstance', 'resume', 'userId', 'baseUrl', '$http', 'Upload', resumeModal]);

function resumeModal($scope, $modalInstance, resume, userId, baseUrl, $http, Upload) {
	
	if(resume) {
		$scope.mode = "Edit";
		$scope.resume = _.omit(resume, 'id', 'fileType', 'uploadedDate');
		$scope.id = resume.id;
		$scope.userId = userId;
	} else {
		$scope.mode = "Add";
		$scope.resume = {title: "", email: "", resumeContact: "", fileUpload: {id: ""}, visibility: ""};
		$scope.uploadStatus = "NoFileUploaded";
		$scope.userId = userId;
	}
	
	$modalInstance.rendered.then(function(){
		$("#resume-title").focus();
	});
	
	$scope.$watch('file', function(file) {
		if(file) {
			$scope.upload($scope.file);
		}
	});
	

	$scope.upload = function (file) {
        Upload.upload({
            url: baseUrl + 'api/fileUpload',
            fields: {'type': 'resume'},
            file: file
        }).progress(function (evt) {
    		$scope.uploadStatus = "Uploading";
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
    		$scope.uploadProgress = progressPercentage;
        }).success(function (data, status, headers, config) {
            $scope.uploadedFile = {name: config.file.name, id: data.id};
            toastr.success("File uploaded successfully.", "Success");
            $scope.resume.fileUpload.id = data.id;
    		$scope.uploadStatus = "Uploaded";
    		$scope.fileType = data.fileType;
        }).error(function (data, status, headers, config) {
            toastr.error("Something went wrong", "Error");
    		$scope.uploadStatus = "NoFileUploaded";
        })
    };
    

	$scope.add = function() {

		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addResumeForm.$valid) {
			toastr.error("Please fill required fields.", "Error");
			return;
		}
		
		if($scope.mode == "Add") {
			var promise = $http.post(baseUrl + 'api/heroes/' + $scope.userId + '/resumes', $scope.resume); 
		} else {
			var promise = $http.put(baseUrl + 'api/resumes/' + $scope.id, $scope.resume);
		}
		
		promise.then(function(response) {
			if(response.data.status == "success") {
				toastr.success(response.data.message, "Success");
				if($scope.mode == "Add"){
					$scope.resume.id = response.data.id;
					$scope.resume.fileType=$scope.fileType;	
				} else { 
					$scope.resume.id = $scope.id;
					$scope.resume.fileType=resume.fileType;		
				}
				$modalInstance.close($scope.resume);
			} else {
				toastr.error("Something went wrong.", "Error");
			}
		}, function() {
			toastr.error("Something went wrong.", "Error");
		});
	};
	
	$scope.deleteFile = function(uploadedFileId) {
		$http.delete(baseUrl + 'api/fileUpload/' + uploadedFileId)
			.then(function(response) {
				if(response.data.status == "success") {
					toastr.success(response.data.message, "Success");
		            $scope.uploadedFile = {name: "", id: ""};
		            $scope.resume.fileUpload.id = "";            
		    		$scope.uploadStatus = "NoFileUploaded";
				} else {
					toastr.error("Something went wrong.", "Error");
				}
		}, function() {
			toastr.error("Something went wrong.", "Error");
		});
	}
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}

app.controller('SetResumeController', [ '$scope', '$http', 'baseUrl',setResumeController]);
function setResumeController($scope, $http, baseUrl) {    
}

app.controller('ResumeController', [ '$scope', '$http', 'baseUrl',resumeController]);
function resumeController($scope, $http, baseUrl) {
}
