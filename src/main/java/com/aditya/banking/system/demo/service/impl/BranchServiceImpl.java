package com.aditya.banking.system.demo.service.impl;

import com.aditya.banking.system.demo.dao.BankRepository;
import com.aditya.banking.system.demo.dao.BranchRepository;
import com.aditya.banking.system.demo.entity.constant.enums.ResponseCode;
import com.aditya.banking.system.demo.entity.dao.Branch;
import com.aditya.banking.system.demo.exception.BusinessLogicException;
import com.aditya.banking.system.demo.service.api.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BranchServiceImpl implements BranchService {
    private static final Logger LOG = LoggerFactory.getLogger(BranchServiceImpl.class);

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public Branch saveBranch(Long userId, Branch branch) {
        if (adminService.isAdminUser(userId)) {
            if (bankRepository.existsById(branch.getBankId())) {
                try {
                    branch.setCreatedBy(userId);
                    branch.setCreatedDate(new Date());
                    branch.setUpdatedBy(userId);
                    branch.setUpdatedDate(new Date());
                    return branchRepository.save(branch);
                } catch (Exception exception) {
                    LOG.error("Error in saving the Branch details ");
                    throw new BusinessLogicException(ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getCode(), ResponseCode.DUPLICATE_REQUEST_BODY_FIELDS.getMessage());
                }
            } else {
                LOG.info("Bank does not exists for give branch : {}", branch.getBankId());
                throw new BusinessLogicException(ResponseCode.BANK_DOES_NOT_EXISTS.getCode(), ResponseCode.BANK_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public Branch getBranchDetails(Long userId, Long branchId) {
        if (adminService.isAdminUser(userId)) {
            if (branchRepository.existsById(branchId)) {
                return branchRepository.findById(branchId).get();
            } else {
                LOG.info("Branch does not exists : {} ", branchId);
                throw new BusinessLogicException(ResponseCode.BRANCH_DOES_NOT_EXISTS.getCode(), ResponseCode.BRANCH_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public Branch updateBranch(Long userId, Branch branch, Long branchId) {
        if (adminService.isAdminUser(userId)) {
            if (branchRepository.existsById(branchId)) {
                Branch savedBranch = branchRepository.findById(branchId).get();
                branch.setId(branchId);
                branch.setUpdatedBy(userId);
                branch.setUpdatedDate(new Date());
                branch.setCreatedBy(savedBranch.getCreatedBy());
                branch.setCreatedDate(savedBranch.getCreatedDate());
                return branchRepository.save(branch);
            } else {
                LOG.info("Branch does not exists : {} ", branchId);
                throw new BusinessLogicException(ResponseCode.BRANCH_DOES_NOT_EXISTS.getCode(), ResponseCode.BRANCH_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not admin : {} ", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }

    @Override
    public void deleteBranch(Long userId, Long branchId) {
        if (adminService.isAdminUser(userId)) {
            if (branchRepository.existsById(branchId)) {
                branchRepository.deleteById(branchId);
            } else {
                LOG.info("Branch does not exists : {} ", branchId);
                throw new BusinessLogicException(ResponseCode.BRANCH_DOES_NOT_EXISTS.getCode(), ResponseCode.BRANCH_DOES_NOT_EXISTS.getMessage());
            }
        } else {
            LOG.info("You are not an admin : {}", userId);
            throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED_ERROR.getCode(), ResponseCode.NOT_AUTHORIZED_ERROR.getMessage());
        }
    }
}
